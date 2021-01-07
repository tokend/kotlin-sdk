package org.tokend.sdk.test

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonMappingException
import com.google.gson.annotations.SerializedName
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.factory.GsonFactory
import org.tokend.sdk.factory.JsonApiToolsProvider
import java.math.BigDecimal
import java.text.ParseException
import java.util.*

class JsonSerialization {
    private data class BigDecimalContainer(
            @SerializedName("v")
            @JsonProperty("v")
            val v: BigDecimal?
    )

    private data class DateContainer(
            @SerializedName("v")
            @JsonProperty("v")
            val v: Date?
    )

    @Test
    fun gsonBigDecimal() {
        Assert.assertEquals(
                "{\"v\":\"3.14\"}",
                GsonFactory().getBaseGson().toJson(BigDecimalContainer(BigDecimal("3.14")))
        )
        Assert.assertEquals(
                "{\"v\":null}",
                GsonFactory().getBaseGson().toJson(BigDecimalContainer(null))
        )

        Assert.assertEquals(
                BigDecimalContainer(BigDecimal("3.14")),
                GsonFactory().getBaseGson().fromJson("{\"v\":\"3.14\"}", BigDecimalContainer::class.java)
        )
        Assert.assertEquals(
                BigDecimalContainer(null),
                GsonFactory().getBaseGson().fromJson("{\"v\":null}", BigDecimalContainer::class.java)
        )
    }

    @Test
    fun gsonDate() {
        Assert.assertEquals(
                "{\"v\":\"2021-01-07T10:31:07.000Z\"}",
                GsonFactory().getBaseGson().toJson(DateContainer(Date(1610015467000)))
        )
        Assert.assertEquals(
                "{\"v\":null}",
                GsonFactory().getBaseGson().toJson(DateContainer(null))
        )

        Assert.assertEquals(
                DateContainer(Date(1610015467000)),
                GsonFactory().getBaseGson().fromJson("{\"v\":\"2021-01-07T10:31:07.000Z\"}", DateContainer::class.java)
        )
        Assert.assertEquals(
                DateContainer(null),
                GsonFactory().getBaseGson().fromJson("{\"v\":null}", DateContainer::class.java)
        )
    }

    @Test
    fun jacksonBigDecimal() {
        Assert.assertEquals(
                "{\"v\":\"3.14\"}",
                JsonApiToolsProvider.getObjectMapper().writeValueAsString(BigDecimalContainer(BigDecimal("3.14")))
        )
        Assert.assertEquals(
                "{\"v\":null}",
                JsonApiToolsProvider.getObjectMapper().writeValueAsString(BigDecimalContainer(null))
        )

        Assert.assertEquals(
                BigDecimalContainer(BigDecimal("3.14")),
                JsonApiToolsProvider.getObjectMapper().readValue("{\"v\":\"3.14\"}", BigDecimalContainer::class.java)
        )
        Assert.assertEquals(
                BigDecimalContainer(null),
                JsonApiToolsProvider.getObjectMapper().readValue("{\"v\":null}", BigDecimalContainer::class.java)
        )
    }

    @Test
    fun jacksonDate() {
        Assert.assertEquals(
                "{\"v\":\"2021-01-07T10:31:07.000Z\"}",
                JsonApiToolsProvider.getObjectMapper().writeValueAsString(DateContainer(Date(1610015467000)))
        )
        Assert.assertEquals(
                "{\"v\":null}",
                JsonApiToolsProvider.getObjectMapper().writeValueAsString(DateContainer(null))
        )

        Assert.assertEquals(
                DateContainer(Date(1610015467000)),
                JsonApiToolsProvider.getObjectMapper().readValue("{\"v\":\"2021-01-07T10:31:07.000Z\"}", DateContainer::class.java)
        )
        Assert.assertEquals(
                DateContainer(null),
                JsonApiToolsProvider.getObjectMapper().readValue("{\"v\":null}", DateContainer::class.java)
        )
    }

    @Test(expected = ParseException::class)
    fun gsonDateMalformed() {
        GsonFactory().getBaseGson().fromJson("{\"v\":\"01 Jan 2021 12:35 PM\"}", DateContainer::class.java)
    }

    @Test(expected = JsonMappingException::class)
    fun jacksonDateMalformed() {
        JsonApiToolsProvider.getObjectMapper().readValue("{\"v\":\"01 Jan 2021 12:35 PM\"}", DateContainer::class.java)
    }
}