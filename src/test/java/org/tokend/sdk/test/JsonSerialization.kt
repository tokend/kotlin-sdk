package org.tokend.sdk.test

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonMappingException
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.factory.JsonApiToolsProvider
import java.math.BigDecimal
import java.util.*

class JsonSerialization {
    private data class BigDecimalContainer(
        @JsonProperty("v")
        val v: BigDecimal?
    )

    private data class DateContainer(
        @JsonProperty("v")
        val v: Date?
    )

    @Test
    fun jacksonBigDecimal() {
        Assert.assertEquals(
            "{\"v\":\"3.14\"}",
            JsonApiToolsProvider.getObjectMapper()
                .writeValueAsString(BigDecimalContainer(BigDecimal("3.14")))
        )
        Assert.assertEquals(
            "{}",
            JsonApiToolsProvider.getObjectMapper().writeValueAsString(BigDecimalContainer(null))
        )

        Assert.assertEquals(
            BigDecimalContainer(BigDecimal("3.14")),
            JsonApiToolsProvider.getObjectMapper()
                .readValue("{\"v\":\"3.14\"}", BigDecimalContainer::class.java)
        )
        Assert.assertEquals(
            BigDecimalContainer(null),
            JsonApiToolsProvider.getObjectMapper()
                .readValue("{\"v\":null}", BigDecimalContainer::class.java)
        )
    }

    @Test
    fun jacksonDate() {
        Assert.assertEquals(
            "{\"v\":\"2021-01-07T10:31:07.000Z\"}",
            JsonApiToolsProvider.getObjectMapper()
                .writeValueAsString(DateContainer(Date(1610015467000)))
        )
        Assert.assertEquals(
            "{}",
            JsonApiToolsProvider.getObjectMapper().writeValueAsString(DateContainer(null))
        )

        Assert.assertEquals(
            DateContainer(Date(1610015467000)),
            JsonApiToolsProvider.getObjectMapper()
                .readValue("{\"v\":\"2021-01-07T10:31:07.000Z\"}", DateContainer::class.java)
        )
        Assert.assertEquals(
            DateContainer(null),
            JsonApiToolsProvider.getObjectMapper()
                .readValue("{\"v\":null}", DateContainer::class.java)
        )
    }

    @Test(expected = JsonMappingException::class)
    fun jacksonDateMalformed() {
        JsonApiToolsProvider.getObjectMapper()
            .readValue("{\"v\":\"01 Jan 2021 12:35 PM\"}", DateContainer::class.java)
    }
}