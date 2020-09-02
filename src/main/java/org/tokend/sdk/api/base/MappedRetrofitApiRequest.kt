package org.tokend.sdk.api.base

import okhttp3.MediaType
import okhttp3.ResponseBody
import org.tokend.sdk.api.base.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.InterruptedIOException

/**
 * API request based on Retrofit [Call] with response and error mapping.
 * @param call base Retrofit call
 * @param responseMapper transformation function for the response
 * @param errorMapper transformation function for errors
 */
open class MappedRetrofitApiRequest<CallType, ResponseType>(
        protected val call: Call<CallType>,
        protected val responseMapper: (CallType) -> ResponseType,
        protected val errorMapper: ((Throwable) -> Throwable)? = null
) : ApiRequest<ResponseType> {
    protected var asyncCallback: ApiCallback<ResponseType>? = null

    override fun execute(): ApiResponse<ResponseType> {
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                ApiResponse(
                        mapResponse(response.body())
                )
            } else {
                throw HttpException(response)
            }
        } catch (error: Throwable) {
            throw mapError(error)
        }
    }

    override fun executeAsync(callback: ApiCallback<ResponseType>) {
        synchronized(this) {
            if (asyncCallback != null) {
                return
            }

            asyncCallback = callback

            call.enqueue(object : Callback<CallType> {
                override fun onFailure(call: Call<CallType>, t: Throwable) {
                    if (!isCanceled()) {
                        asyncCallback?.onError(mapError(t))
                    }
                }

                override fun onResponse(call: Call<CallType>, response: Response<CallType>) {
                    if (!isCanceled()) {
                        if (response.isSuccessful) {
                            try {
                                asyncCallback?.onSuccess(
                                        ApiResponse(
                                                mapResponse(response.body())
                                        )
                                )
                            } catch (error: Throwable) {
                                asyncCallback?.onError(
                                        mapError(error)
                                )
                            }
                        } else {
                            asyncCallback?.onError(
                                    mapError(HttpException(response))
                            )
                        }
                    }
                }
            })
        }
    }

    override fun cancel() {
        synchronized(this) {
            call.cancel()
            asyncCallback?.onError(mapError(InterruptedIOException("Canceled by user")))
            asyncCallback = null
        }
    }

    override fun isCanceled(): Boolean {
        return synchronized(this) { call.isCanceled }
    }

    protected open fun mapError(error: Throwable): Throwable {
        return errorMapper?.invoke(error) ?: error
    }

    protected open fun mapResponse(response: CallType): ResponseType {
        return if (response == null) {
            try {
                responseMapper(VOID as CallType)
            } catch (_: Throwable) {
                try {
                    responseMapper(UNIT as CallType)
                } catch (_: Throwable) {
                    try {
                        responseMapper(EMPTY_JSON_BODY as CallType)
                    } catch (_: Throwable) {
                        response as ResponseType
                    }
                }
            }
        } else if (response is Void || response is Unit) {
            response as ResponseType
        } else {
            responseMapper(response)
        }
    }

    override fun <MappedResponseType> map(mapper: (ResponseType) -> MappedResponseType)
            : MappedRetrofitApiRequest<CallType, MappedResponseType> {
        val newMapper: (CallType) -> MappedResponseType = {
            responseMapper.invoke(it).let(mapper)
        }

        return MappedRetrofitApiRequest(call, newMapper, errorMapper)
    }

    override fun clone(): MappedRetrofitApiRequest<CallType, ResponseType> {
        return MappedRetrofitApiRequest(
                call.clone(),
                responseMapper,
                errorMapper
        )
    }

    private companion object {
        private val UNIT: Unit =
                Unit::class.java.getDeclaredConstructor().run {
                    isAccessible = true
                    val instance = newInstance()
                    isAccessible = false
                    instance as Unit
                }
        private val VOID: Void =
                Void::class.java.getDeclaredConstructor().run {
                    isAccessible = true
                    val instance = newInstance()
                    isAccessible = false
                    instance as Void
                }
        private val EMPTY_JSON_BODY =
                ResponseBody.create(MediaType.parse("application/json"), "{}")
    }
}