package org.tokend.sdk.api.base

import retrofit2.Call

class SimpleRetrofitApiRequest<T>(
        call: Call<T>,
        errorMapper: ((Throwable) -> Throwable)? = null
) : MappedRetrofitApiRequest<T, T>(call, { it }, errorMapper)