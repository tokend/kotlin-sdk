package org.tokend.sdk.helpers.buildergen

import org.tokend.sdk.api.v3.base.PageQueryParams
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

/**
 * Generates Builder class for page query params
 */
class PageQueryParamsBuilderGenerator(
        private val clazz: KClass<out PageQueryParams>
) {
    fun generate(): String {
        val propertyNames = clazz.declaredMemberProperties
                .map { it.name }
                .toSet()

        val primaryConstructor = clazz.primaryConstructor
                ?: throw IllegalArgumentException("Query params class has no primary constructor")

        val primaryConstructorParams = primaryConstructor.parameters

        val propertyRelatedConstructorParams = primaryConstructorParams.filter {
            propertyNames.contains(it.name)
        }

        val requiredProperties = propertyRelatedConstructorParams.filter { !it.isOptional }
        val optionalProperties = propertyRelatedConstructorParams.filter { it.isOptional }

        val primaryConstructorWithRequiredProperties =
                if (requiredProperties.isEmpty())
                    ""
                else
                    "(" + requiredProperties
                            .map { "protected open val ${it.name}: ${it.type}" }
                            .joinToString(", ") + ")"

        val optionalPropertiesDeclarations = optionalProperties
                .map { "    protected open var ${it.name}: ${it.type} = null" }
                .joinToString("\n")

        val optionalPropertiesSetters = optionalProperties
                .map {
                    val name = it.name
                    "    open fun with${name?.capitalize()}" +
                            "($name: ${it.type.toString().trimEnd('?')}) = " +
                            "also { this.$name = $name }"
                }
                .joinToString("\n\n")

        val overriddenSetters = """
        |    override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }
        |
        |    override fun withInclude(vararg include: String) = also { super.withInclude(*include) }
        |
        |    override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }
        """.trimMargin()

        val queryParamsName = clazz.simpleName

        val buildMethod = """
            |    override fun build(): $queryParamsName =
            |        $queryParamsName(${primaryConstructorParams.map { it.name }.joinToString()})
        """.trimMargin()

        val header = "open class $BUILDER_NAME$primaryConstructorWithRequiredProperties" +
                ": PageQueryParams.Builder() {"

        return header + "\n" +
                optionalPropertiesDeclarations + "\n\n" +
                optionalPropertiesSetters + "\n\n" +
                overriddenSetters + "\n\n" +
                buildMethod + "\n" +
                "}\n"
    }

    private companion object {
        private const val BUILDER_NAME = "Builder"
    }
}