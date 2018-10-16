package org.tokend.sdk.api.base

import org.tokend.sdk.api.base.model.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

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
        call.enqueue(object : Callback<CallType> {
            override fun onFailure(call: Call<CallType>, t: Throwable) {
                if (!call.isCanceled) {
                    callback.onError(mapError(t))
                }
            }

            override fun onResponse(call: Call<CallType>, response: Response<CallType>) {
                if (!call.isCanceled) {
                    if (response.isSuccessful) {
                        try {
                            callback.onSuccess(
                                    ApiResponse(
                                            mapResponse(response.body())
                                    )
                            )
                        } catch (error: Throwable) {
                            callback.onError(
                                    mapError(error)
                            )
                        }
                    } else {
                        callback.onError(
                                mapError(HttpException(response))
                        )
                    }
                }
            }
        })
    }

    override fun cancel() {
        call.cancel()
    }

    override fun isCanceled(): Boolean {
        return call.isCanceled
    }

    protected open fun mapError(error: Throwable): Throwable {
        return errorMapper?.invoke(error) ?: error
    }

    protected open fun mapResponse(response: CallType): ResponseType {
        return if (response is Void || response == null)
            response as ResponseType
        else
            responseMapper(response)
    }
}