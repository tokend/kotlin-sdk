package org.tokend.sdk.api.base

import retrofit2.Call

/**
 * API request based on Retrofit [Call] with error mapping.
 * @see MappedRetrofitApiRequest
 */
class SimpleRetrofitApiRequest<T>(
        call: Call<T>,
        errorMapper: ((Throwable) -> Throwable)? = null
) : MappedRetrofitApiRequest<T, T>(call, { it }, errorMapper)