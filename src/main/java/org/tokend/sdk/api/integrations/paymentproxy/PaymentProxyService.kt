package org.tokend.sdk.api.integrations.paymentproxy

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.integrations.paymentproxy.model.PaymentAccountResource
import retrofit2.Call
import retrofit2.http.GET

interface PaymentProxyService {
    @GET("integrations/payment-proxy/info")
    fun getInfo(): Call<JSONAPIDocument<PaymentAccountResource>>
}