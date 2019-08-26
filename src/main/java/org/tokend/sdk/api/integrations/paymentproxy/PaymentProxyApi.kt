package org.tokend.sdk.api.integrations.paymentproxy

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.integrations.paymentproxy.model.PaymentAccountResource

open class PaymentProxyApi(
        protected open val paymentProxyService: PaymentProxyService
) {
    open fun getInfo(): ApiRequest<PaymentAccountResource> {
        return MappedRetrofitApiRequest(
                paymentProxyService.getInfo(),
                JSONAPIDocument<PaymentAccountResource>::get
        )
    }
}