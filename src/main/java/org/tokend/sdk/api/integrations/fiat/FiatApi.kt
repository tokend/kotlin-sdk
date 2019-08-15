package org.tokend.sdk.api.integrations.fiat

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.integrations.fiat.model.FiatInvoiceAttributes
import org.tokend.sdk.api.v3.transactions.model.SubmitTransactionRequestBody
import org.tokend.wallet.Transaction

open class FiatApi(
        protected open val fiatService: FiatService
) {
    /**
     * @return fiat invoice initiated by submitting of given atomic swap bid transaction
     */
    open fun submitBidTransaction(transaction: Transaction):
            ApiRequest<FiatInvoiceAttributes> = submitBidTransaction(transaction.getEnvelope().toBase64())

    /**
     * @return fiat invoice initiated by submitting of given atomic swap bid transaction
     */
    open fun submitBidTransaction(envelopeBase64: String): ApiRequest<FiatInvoiceAttributes> {
        return MappedRetrofitApiRequest(
                fiatService.submitBidTransaction(
                        SubmitTransactionRequestBody(
                                envelopeBase64,
                                waitForIngest = false
                        )
                ),
                { it.data.attributes }
        )
    }
}