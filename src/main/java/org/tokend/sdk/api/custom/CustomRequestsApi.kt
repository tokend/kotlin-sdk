package org.tokend.sdk.api.custom

import com.github.jasminb.jsonapi.JSONAPIDocument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

/**
 * Allows to make custom HTTP requests with response body mapping.
 *
 * If response class extends [BaseResource], then [JsonApiToolsProvider] will be used for mapping.
 * If response class is [String] or [ByteArray] or primitive Java byte array,
 * then no mapping will be performed.
 * Otherwise [Gson] will be used for mapping.
 */
open class CustomRequestsApi(
        protected open val customRequestsService: CustomRequestsService
) {
    protected open val gson = GsonFactory().getBaseGson()
    protected open val jsonApiResourceConverter = JsonApiToolsProvider.getResourceConverter()

    // region GET
    protected open fun doGet(url: String, queryMap: Map<String, Any>?, headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.get(
                    url = url,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    /**
     * Performs GET HTTP request.
     * [Type]-based variant is used if [T] is a generic.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param responseType [Type] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     *
     * @see TypeToken
     */
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

    /**
     * Performs GET HTTP request.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param responseClass [Class] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
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

    /**
     * Performs GET HTTP request.
     *
     * @return [DataPage] with items of type [T]
     *
     * @param url Relative endpoint URL
     * @param pageItemClass [Class] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
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
    protected open fun doPost(url: String, body: Any?, queryMap: Map<String, Any>?,
                              headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.post(
                    url = url,
                    body = body,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    /**
     * Performs POST HTTP request.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param body Request body, will be serialized to JSON with [Gson]
     * @param responseClass [Class] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
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

    /**
     * Performs POST HTTP request.
     *
     * [Type]-based variant is used if [T] is a generic.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param body Request body, will be serialized to JSON with [Gson]
     * @param responseType [Type] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
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
    protected open fun doPut(url: String, body: Any?, queryMap: Map<String, Any>?,
                             headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.put(
                    url = url,
                    body = body,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    /**
     * Performs PUT HTTP request.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param body Request body, will be serialized to JSON with [Gson]
     * @param responseClass [Class] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
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

    /**
     * Performs PUT HTTP request.
     *
     * [Type]-based variant is used if [T] is a generic.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param body Request body, will be serialized to JSON with [Gson]
     * @param responseType [Type] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
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
    protected open fun doPatch(url: String, body: Any?, queryMap: Map<String, Any>?,
                               headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.patch(
                    url = url,
                    body = body,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    /**
     * Performs PATCH HTTP request.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param body Request body, will be serialized to JSON with [Gson]
     * @param responseClass [Class] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
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

    /**
     * Performs PATCH HTTP request.
     *
     * [Type]-based variant is used if [T] is a generic.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param body Request body, will be serialized to JSON with [Gson]
     * @param responseType [Type] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
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
    protected open fun doDelete(url: String, queryMap: Map<String, Any>?, headersMap: Map<String, Any>?): Call<ResponseBody> =
            customRequestsService.delete(
                    url = url,
                    query = queryMap ?: emptyMap(),
                    headers = headersMap ?: emptyMap()
            )

    /**
     * Performs DELETE HTTP request.
     *
     * [Type]-based variant is used if [T] is a generic.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param responseType [Type] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
    @JvmOverloads
    open fun <T> delete(url: String,
                        responseType: Type,
                        queryMap: Map<String, Any>? = null,
                        headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doDelete(url, queryMap, headersMap),
                { mapResponseByType<T>(it, responseType) }
        )
    }

    /**
     * Performs DELETE HTTP request.
     *
     * @return Response of type [T]
     *
     * @param url Relative endpoint URL
     * @param responseClass [Class] that matches [T]
     * @param queryMap Map of query params
     * @param headersMap Map of headers
     */
    @JvmOverloads
    open fun <T> delete(url: String,
                        responseClass: Class<out T>,
                        queryMap: Map<String, Any>? = null,
                        headersMap: Map<String, Any>? = null): ApiRequest<T> {
        return MappedRetrofitApiRequest(
                doDelete(url, queryMap, headersMap),
                { mapResponseByClass(it, responseClass) }
        )
    }
    // endregion

    protected open fun <T> mapResponseByClass(responseBody: ResponseBody,
                                              responseClass: Class<out T>): T {
        return when {
            BaseResource::class.java.isAssignableFrom(responseClass) ->
                jsonApiResourceConverter.readDocument(
                        responseBody.byteStream(),
                        responseClass
                ).get()
            responseClass.name == "java.lang.String" ->
                responseBody.string() as T
            responseClass.name == "[B" ->
                responseBody.bytes() as T
            else ->
                gson.fromJson<T>(responseBody.charStream(), responseClass)

        }
    }

    protected open fun <T> mapResponseByType(responseBody: ResponseBody,
                                             responseType: Type): T {
        return gson.fromJson<T>(responseBody.charStream(), responseType)
    }

    protected open fun <T> mapPageResponseByClass(responseBody: ResponseBody,
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