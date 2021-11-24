package org.tokend.sdk.test.integration

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.type.TypeReference
import com.github.jasminb.jsonapi.JSONAPIDocument
import com.github.jasminb.jsonapi.Links
import com.github.jasminb.jsonapi.annotations.Type
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer
import org.junit.*
import org.junit.runners.MethodSorters
import org.tokend.sdk.api.TokenDApi
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.Link
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.factory.JsonApiToolsProvider
import java.net.HttpURLConnection
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.util.*

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CustomRequestsTest {
    private data class DummyEntity(
        @JsonProperty("field_a")
        val a: Int,
        @JsonProperty("field_b")
        val b: String
    ) {
        companion object {
            val DEFAULT = DummyEntity(42, "TEST")
        }
    }

    @Type(DummyResource.TYPE)
    @JsonIgnoreProperties(ignoreUnknown = true)
    private class DummyResource : BaseResource() {
        @JsonProperty("attribute_a")
        var a: Int = 0

        @JsonProperty("attribute_b")
        var b: String = ""

        override fun isFilled(): Boolean {
            return a != 0 && b != ""
        }

        override fun equals(other: Any?): Boolean {
            return other is DummyResource && other.id == this.id && other.a == this.a && other.b == this.b
        }

        override fun hashCode(): Int {
            return Objects.hash(id, a, b)
        }

        companion object {
            const val TYPE = "dummy_resource"
            val DEFAULT = DummyResource().apply {
                id = "42"
                a = 404
                b = "test"
            }
        }
    }

    private val defaultQueryMap = mapOf(
        "param_a" to 1,
        "param_b" to "test",
        "param_c" to "long string"
    )
    private val defaultQueryMapEncoded = "?param_a=1&param_b=test&param_c=long%20string"

    private val defaultHeadersMap = mapOf(
        "X_header_a" to 1,
        "X_header_b" to "test",
        "X_header_c" to "long string"
    )
    private val defaultHeadersString = defaultHeadersMap.entries.joinToString()

    companion object {
        private var port: Int = 0
        private lateinit var server: HttpServer

        private var lastRequestMethod: String = ""
        private var lastRequestUrl: String = ""
        private var lastRequestBody: String = ""
        private var lastRequestHeadersString: String = ""

        @JvmStatic
        @BeforeClass
        fun setUpTestServer() {
            JsonApiToolsProvider.reset()
            JsonApiToolsProvider.addExtraResources(DummyResource::class.java)

            val socket = ServerSocket(0).apply { reuseAddress = true }
            port = socket.localPort
            socket.close()
            server = HttpServer.create(InetSocketAddress(port), 0)

            val captureRequestParams = { http: HttpExchange ->
                lastRequestMethod = http.requestMethod
                lastRequestUrl = http.requestURI.toString()
                lastRequestBody = http.requestBody.bufferedReader(Charsets.UTF_8).readText()
                lastRequestHeadersString = http.requestHeaders.toMap()
                    .mapValues { it.value.first() }
                    .filterKeys { it.startsWith("X_") }
                    .toSortedMap()
                    .entries
                    .joinToString()
            }

            server.createContext("/dummy") { http ->
                captureRequestParams(http)

                val response = JsonApiToolsProvider.getObjectMapper()
                    .writeValueAsBytes(DummyEntity.DEFAULT)

                http.responseHeaders.add("Content-type", "application/json")
                http.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.size.toLong())
                http.responseBody.use { it.write(response) }
            }

            server.createContext("/dummy_list") { http ->
                captureRequestParams(http)

                val response = JsonApiToolsProvider.getObjectMapper()
                    .writeValueAsBytes(listOf(DummyEntity.DEFAULT))

                http.responseHeaders.add("Content-type", "application/json")
                http.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.size.toLong())
                http.responseBody.use { it.write(response) }
            }

            server.createContext("/dummy_page") { http ->
                captureRequestParams(http)

                val page = mapOf(
                    "_embedded" to mapOf(
                        "records" to listOf(DummyEntity.DEFAULT)
                    ),
                    "_links" to mapOf(
                        "self" to Link("/dummy_page?page=0&limit=10"),
                        "next" to Link("/dummy_page?page=1&limit=10")
                    )
                )
                val response = JsonApiToolsProvider.getObjectMapper()
                    .writeValueAsBytes(page)

                http.responseHeaders.add("Content-type", "application/json")
                http.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.size.toLong())
                http.responseBody.use { it.write(response) }
            }

            server.createContext("/dummy_resource") { http ->
                captureRequestParams(http)

                val response = JsonApiToolsProvider.getResourceConverter().writeDocument(
                    JSONAPIDocument(DummyResource.DEFAULT)
                )

                http.responseHeaders.add("Content-type", "application/vnd.api+json")
                http.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.size.toLong())
                http.responseBody.use { it.write(response) }
            }

            server.createContext("/dummy_resource_page") { http ->
                captureRequestParams(http)

                val response = JsonApiToolsProvider.getResourceConverter().writeDocumentCollection(
                    JSONAPIDocument(
                        listOf(DummyResource.DEFAULT),
                        Links(
                            mapOf(
                                "self" to com.github.jasminb.jsonapi.Link("/dummy_resource_page?page%5Blimit%5D=10&page%5Bnumber%5D=0"),
                                "next" to com.github.jasminb.jsonapi.Link("/dummy_resource_page?page%5Blimit%5D=10&page%5Bnumber%5D=1")
                            )
                        ),
                        emptyMap()
                    )
                )

                http.responseHeaders.add("Content-type", "application/vnd.api+json")
                http.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.size.toLong())
                http.responseBody.use { it.write(response) }
            }

            server.createContext("/no_content") { http ->
                captureRequestParams(http)

                http.sendResponseHeaders(HttpURLConnection.HTTP_NO_CONTENT, -1)
                http.responseBody.close()
            }

            server.start()
        }

        @AfterClass
        @JvmStatic
        fun stopTestServer() {
            server.stop(0)
        }
    }

    private fun getApi(): CustomRequestsApi {
        return TokenDApi("http://localhost:$port").customRequests
    }

    @Test
    fun getWithClass() {
        val response = getApi()
            .get("/dummy", DummyEntity::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()
            .get()

        Assert.assertEquals("GET", lastRequestMethod)
        Assert.assertEquals("/dummy$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(DummyEntity.DEFAULT, response)
    }

    @Test
    fun getWithType() {
        val response = getApi()
            .get(
                "/dummy_list",
                object : TypeReference<List<DummyEntity>>() {},
                defaultQueryMap,
                defaultHeadersMap
            )
            .execute()
            .get()
            .first()

        Assert.assertEquals("GET", lastRequestMethod)
        Assert.assertEquals("/dummy_list$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(DummyEntity.DEFAULT, response)
    }

    @Test
    fun getNoContent() {
        val response = getApi()
            .get("/no_content", Void::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()

        Assert.assertEquals("GET", lastRequestMethod)
        Assert.assertEquals("/no_content$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertFalse(response.hasValue())
    }

    @Test
    fun getResource() {
        val response = getApi()
            .get("/dummy_resource", DummyResource::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()
            .get()

        Assert.assertEquals("GET", lastRequestMethod)
        Assert.assertEquals("/dummy_resource$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(DummyResource.DEFAULT, response)
    }

    @Test
    fun getResourcePage() {
        val responsePage = getApi()
            .getPage(
                "/dummy_resource_page",
                DummyResource::class.java,
                defaultQueryMap,
                defaultHeadersMap
            )
            .execute()
            .get()

        Assert.assertEquals("GET", lastRequestMethod)
        Assert.assertEquals("/dummy_resource_page$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertTrue(responsePage.isLast)
        Assert.assertEquals(DummyResource.DEFAULT, responsePage.items.first())
    }

    @Test
    fun getPage() {
        val responsePage = getApi()
            .getPage("/dummy_page", DummyEntity::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()
            .get()

        Assert.assertEquals("GET", lastRequestMethod)
        Assert.assertEquals("/dummy_page$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertTrue(responsePage.isLast)
        Assert.assertEquals(DummyEntity.DEFAULT, responsePage.items.first())
    }

    @Test
    fun post() {
        val data = mapOf("test" to "data")

        val response = getApi()
            .post("/dummy", data, DummyEntity::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()
            .get()

        Assert.assertEquals("POST", lastRequestMethod)
        Assert.assertEquals("/dummy$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(
            JsonApiToolsProvider.getObjectMapper().writeValueAsString(data),
            lastRequestBody
        )
        Assert.assertEquals(DummyEntity.DEFAULT, response)
    }

    @Test
    fun postNoContentResponse() {
        val data = mapOf("test" to "data")

        val response = getApi()
            .post("/no_content", data, Void::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()

        Assert.assertEquals("POST", lastRequestMethod)
        Assert.assertEquals("/no_content$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(
            JsonApiToolsProvider.getObjectMapper().writeValueAsString(data),
            lastRequestBody
        )
        Assert.assertFalse(response.hasValue())
    }

    @Test
    fun put() {
        val data = mapOf("test" to "data")

        val response = getApi()
            .put("/dummy", data, DummyEntity::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()
            .get()

        Assert.assertEquals("PUT", lastRequestMethod)
        Assert.assertEquals("/dummy$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(
            JsonApiToolsProvider.getObjectMapper().writeValueAsString(data),
            lastRequestBody
        )
        Assert.assertEquals(DummyEntity.DEFAULT, response)
    }

    @Test
    fun putNoContentResponse() {
        val data = mapOf("test" to "data")

        val response = getApi()
            .put("/no_content", data, Void::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()

        Assert.assertEquals("PUT", lastRequestMethod)
        Assert.assertEquals("/no_content$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(
            JsonApiToolsProvider.getObjectMapper().writeValueAsString(data),
            lastRequestBody
        )
        Assert.assertFalse(response.hasValue())
    }

    @Test
    fun patch() {
        val data = mapOf("test" to "data")

        val response = getApi()
            .patch("/dummy", data, DummyEntity::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()
            .get()

        Assert.assertEquals("PATCH", lastRequestMethod)
        Assert.assertEquals("/dummy$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(
            JsonApiToolsProvider.getObjectMapper().writeValueAsString(data),
            lastRequestBody
        )
        Assert.assertEquals(DummyEntity.DEFAULT, response)
    }

    @Test
    fun patchNoContentResponse() {
        val data = mapOf("test" to "data")

        val response = getApi()
            .patch("/no_content", data, Void::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()

        Assert.assertEquals("PATCH", lastRequestMethod)
        Assert.assertEquals("/no_content$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(
            JsonApiToolsProvider.getObjectMapper().writeValueAsString(data),
            lastRequestBody
        )
        Assert.assertFalse(response.hasValue())
    }

    @Test
    fun delete() {
        val response = getApi()
            .delete("/dummy", DummyEntity::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()
            .get()

        Assert.assertEquals("DELETE", lastRequestMethod)
        Assert.assertEquals("/dummy$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertEquals(DummyEntity.DEFAULT, response)
    }

    @Test
    fun deleteNoContent() {
        val response = getApi()
            .delete("/no_content", Void::class.java, defaultQueryMap, defaultHeadersMap)
            .execute()

        Assert.assertEquals("DELETE", lastRequestMethod)
        Assert.assertEquals("/no_content$defaultQueryMapEncoded", lastRequestUrl)
        Assert.assertEquals(defaultHeadersString, lastRequestHeadersString)
        Assert.assertFalse(response.hasValue())
    }

    @Test
    fun getString() {
        val response = getApi()
            .get("/dummy", String::class.java)
            .execute()
            .get()

        Assert.assertTrue(response.isNotEmpty())
    }

    @Test
    fun getByteArray() {
        val response = getApi()
            .get("/dummy", ByteArray::class.java)
            .execute()
            .get()

        Assert.assertTrue(response.isNotEmpty())
    }
}