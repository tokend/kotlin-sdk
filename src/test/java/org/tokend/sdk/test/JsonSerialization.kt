package org.tokend.sdk.test

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonMappingException
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.factory.JsonApiTools
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
            JsonApiTools.objectMapper
                .writeValueAsString(BigDecimalContainer(BigDecimal("3.14")))
        )
        Assert.assertEquals(
            "{}",
            JsonApiTools.objectMapper.writeValueAsString(BigDecimalContainer(null))
        )

        Assert.assertEquals(
            BigDecimalContainer(BigDecimal("3.14")),
            JsonApiTools.objectMapper
                .readValue("{\"v\":\"3.14\"}", BigDecimalContainer::class.java)
        )
        Assert.assertEquals(
            BigDecimalContainer(null),
            JsonApiTools.objectMapper
                .readValue("{\"v\":null}", BigDecimalContainer::class.java)
        )
    }

    @Test
    fun jacksonDate() {
        Assert.assertEquals(
            "{\"v\":\"2021-01-07T10:31:07.000Z\"}",
            JsonApiTools.objectMapper
                .writeValueAsString(DateContainer(Date(1610015467000)))
        )
        Assert.assertEquals(
            "{}",
            JsonApiTools.objectMapper.writeValueAsString(DateContainer(null))
        )

        Assert.assertEquals(
            DateContainer(Date(1610015467000)),
            JsonApiTools.objectMapper
                .readValue("{\"v\":\"2021-01-07T10:31:07.000Z\"}", DateContainer::class.java)
        )
        Assert.assertEquals(
            DateContainer(null),
            JsonApiTools.objectMapper
                .readValue("{\"v\":null}", DateContainer::class.java)
        )
    }

    @Test(expected = JsonMappingException::class)
    fun jacksonDateMalformed() {
        JsonApiTools.objectMapper
            .readValue("{\"v\":\"01 Jan 2021 12:35 PM\"}", DateContainer::class.java)
    }
}