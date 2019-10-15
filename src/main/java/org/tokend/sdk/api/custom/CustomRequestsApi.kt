package org.tokend.sdk.api.custom

import com.github.jasminb.jsonapi.JSONAPIDocument
import okhttp3.ResponseBody
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.model.Page
import org.tokend.sdk.factory.GsonFactory
import org.tokend.sdk.factory.JsonApiToolsProvider
import retrofit2.Call
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

open class CustomRequestsApi(
        protected open val customRequestsService: CustomRequestsService
) {
    protected open val gson = GsonFactory().getBaseGson()
    protected open val jsonApiResourceConverter = JsonApiToolsProvider.getResourceConverter()

    // region GET
    private fun doGet(url: String, queryMap: Map<String, Any>?, headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.get(
                    url = url,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    @JvmOverloads
    open fun <T> get(url: String,
                     responseType: Type,
                     queryMap: Map<String, Any>? = null,
                     headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doGet(url, queryMap, headersMap),
                { mapResponseByType<T>(it, responseType) }
        )
    }

    @JvmOverloads
    open fun <T> get(url: String,
                     responseClass: Class<out T>,
                     queryMap: Map<String, Any>? = null,
                     headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doGet(url, queryMap, headersMap),
                { mapResponseByClass(it, responseClass) }
        )
    }

    @JvmOverloads
    open fun <T> getPage(url: String,
                         pageItemClass: Class<out T>,
                         queryMap: Map<String, Any>? = null,
                         headersMap: Map<String, Any>? = null): ApiRequest<DataPage<T>> {
        return MappedRetrofitApiRequest(
                doGet(url, queryMap, headersMap),
                { mapPageResponseByClass(it, pageItemClass) }
        )
    }
    // endregion

    // region POST
    private fun doPost(url: String, body: Any?, queryMap: Map<String, Any>?,
                       headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.post(
                    url = url,
                    body = body,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    @JvmOverloads
    open fun <T> post(url: String,
                      body: Any,
                      responseClass: Class<out T>,
                      queryMap: Map<String, Any>? = null,
                      headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doPost(url, body, queryMap, headersMap),
                { mapResponseByClass(it, responseClass) }
        )
    }

    @JvmOverloads
    open fun <T> post(url: String,
                      body: Any,
                      responseType: Type,
                      queryMap: Map<String, Any>? = null,
                      headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doPost(url, body, queryMap, headersMap),
                { mapResponseByType<T>(it, responseType) }
        )
    }
    // endregion

    // region PUT
    private fun doPut(url: String, body: Any?, queryMap: Map<String, Any>?,
                      headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.put(
                    url = url,
                    body = body,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    @JvmOverloads
    open fun <T> put(url: String,
                     body: Any,
                     responseClass: Class<out T>,
                     queryMap: Map<String, Any>? = null,
                     headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doPut(url, body, queryMap, headersMap),
                { mapResponseByClass(it, responseClass) }
        )
    }

    @JvmOverloads
    open fun <T> put(url: String,
                     body: Any,
                     responseType: Type,
                     queryMap: Map<String, Any>? = null,
                     headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doPut(url, body, queryMap, headersMap),
                { mapResponseByType<T>(it, responseType) }
        )
    }
    // endregion

    // region PATCH
    private fun doPatch(url: String, body: Any?, queryMap: Map<String, Any>?,
                        headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.patch(
                    url = url,
                    body = body,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    @JvmOverloads
    open fun <T> patch(url: String,
                       body: Any,
                       responseClass: Class<out T>,
                       queryMap: Map<String, Any>? = null,
                       headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doPatch(url, body, queryMap, headersMap),
                { mapResponseByClass(it, responseClass) }
        )
    }

    @JvmOverloads
    open fun <T> patch(url: String,
                       body: Any,
                       responseType: Type,
                       queryMap: Map<String, Any>? = null,
                       headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doPatch(url, body, queryMap, headersMap),
                { mapResponseByType<T>(it, responseType) }
        )
    }
    // endregion

    // region DELETE
    private fun doDelete(url: String, queryMap: Map<String, Any>?, headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.delete(
                    url = url,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    @JvmOverloads
    open fun <T> delete(url: String,
                        responseType: Type,
                        queryMap: Map<String, Any>? = null,
                        headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doGet(url, queryMap, headersMap),
                { mapResponseByType<T>(it, responseType) }
        )
    }

    @JvmOverloads
    open fun <T> delete(url: String,
                        responseClass: Class<out T>,
                        queryMap: Map<String, Any>? = null,
                        headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doGet(url, queryMap, headersMap),
                { mapResponseByClass(it, responseClass) }
        )
    }
    // endregion

    private fun <T> mapResponseByClass(responseBody: ResponseBody,
                                       responseClass: Class<out T>): T {
        return if (BaseResource::class.java.isAssignableFrom(responseClass)) {
            jsonApiResourceConverter.readDocument(
                    responseBody.byteStream(),
                    responseClass
            ).get()
        } else {
            gson.fromJson<T>(responseBody.charStream(), responseClass)
        }
    }

    private fun <T> mapResponseByType(responseBody: ResponseBody,
                                      responseType: Type): T {
        return gson.fromJson<T>(responseBody.charStream(), responseType)
    }

    private fun <T> mapPageResponseByClass(responseBody: ResponseBody,
                                           pageItemClass: Class<out T>): DataPage<T> {
        return if (BaseResource::class.java.isAssignableFrom(pageItemClass)) {
            val document = jsonApiResourceConverter.readDocumentCollection(
                    responseBody.byteStream(),
                    pageItemClass
            ) as JSONAPIDocument<List<T>>
            DataPage.fromPageDocument(document)
        } else {
            val type = object : ParameterizedType {
                override fun getRawType(): Type {
                    return Page::class.java
                }

                override fun getActualTypeArguments(): Array<Type> {
                    return arrayOf(pageItemClass)
                }

                override fun getOwnerType(): Type? = null
            }

            val page = gson.fromJson<T>(responseBody.charStream(), type) as Page<T>
            DataPage.fromPage(page)
        }
    }
}