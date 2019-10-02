package org.tokend.sdk.helpers.resourcegen.model

import com.fasterxml.jackson.databind.JsonNode

class InnerEntity(val name: String,
                  val schema: JsonNode) {
    val attributes = Attribute.getFromComponentSchema(schema)

    override fun toString(): String {
        return "Inner('$name')"
    }

    companion object {
        val NAME_PREDICATE = { name: String ->
            !name.startsWith("xdr.")
                    && !name.endsWith("Attributes")
                    && !name.endsWith("Relationships")
        }
    }
}