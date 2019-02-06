package org.tokend.sdk.test.jsonapi

import com.fasterxml.jackson.annotation.JsonProperty
import com.github.jasminb.jsonapi.annotations.Relationship
import com.github.jasminb.jsonapi.annotations.RelationshipLinks
import org.tokend.sdk.api.base.model.BaseResource
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

object JsonApiUtil {
    /**
     * Prints suspicious null values.
     */
    fun checkResourceNullability(entity: Any, checked: MutableSet<Int> = mutableSetOf()) {
        if (checked.contains(entity.hashCode())) {
            return
        } else {
            checked.add(entity.hashCode())
        }

        val properties = try {
            entity::class.memberProperties
        } catch (e: Error) {
            return
        }

        val bypassNulls = entity is BaseResource && !entity.hasAttributes()

        properties
                .filter { property ->
                    property.javaField?.getAnnotation(JsonProperty::class.java) != null
                            || property.javaField?.getAnnotation(Relationship::class.java) != null
                            || property.javaField?.getAnnotation(RelationshipLinks::class.java) != null
                }
                .forEach { property ->
                    property.isAccessible = true

                    val value = property.getter.call(entity)

                    if (value == null && !bypassNulls) {
                        System.err.println("Property '${property.name}' " +
                                "of ${entity::class.qualifiedName} is null, but can it? \uD83E\uDD14")
                    } else if (value != null) {
                        if (value is Collection<*>) {
                            value
                                    .filterNotNull()
                                    .forEach {
                                        checkResourceNullability(it, checked)
                                    }
                        } else {
                            checkResourceNullability(value, checked)
                        }
                    }
                }
    }

    /**
     * @see checkResourceNullability
     */
    fun checkResourceNullability(entities: Collection<Any>, checked: MutableSet<Int> = mutableSetOf()) {
        entities.forEach {
            checkResourceNullability(it, checked)
        }
    }
}