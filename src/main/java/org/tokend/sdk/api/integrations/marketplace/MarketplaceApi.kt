package org.tokend.sdk.api.integrations.marketplace

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceAttributes
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceData
import org.tokend.sdk.api.v3.transactions.model.SubmitTransactionRequestBody
import org.tokend.sdk.factory.GsonFactory
import org.tokend.wallet.Transaction

open class MarketplaceApi(
        protected open val marketplaceService: MarketplaceService
) {
    /**
     * @return invoice initiated by submitting of given atomic swap bid transaction
     */
    open fun submitBidTransaction(transaction: Transaction):
            ApiRequest<MarketplaceInvoiceData> = submitBidTransaction(transaction.getEnvelope().toBase64())

    /**
     * @return invoice initiated by submitting of given atomic swap bid transaction
     */
    open fun submitBidTransaction(envelopeBase64: String): ApiRequest<MarketplaceInvoiceData> {
        return MappedRetrofitApiRequest(
                marketplaceService.submitBidTransaction(
                        SubmitTransactionRequestBody(
                                envelopeBase64,
                                waitForIngest = false
                        )
                ),
                { getInvoiceData(it.data.attributes) }
        )
    }

    protected open fun getInvoiceData(attributes: MarketplaceInvoiceAttributes): MarketplaceInvoiceData {
        val gson = GsonFactory().getBaseGson()

        return when (attributes.type) {
            MarketplaceInvoiceData.Crypto.TYPE ->
                gson.fromJson(attributes.data, MarketplaceInvoiceData.Crypto::class.java)
            MarketplaceInvoiceData.Redirect.TYPE ->
                gson.fromJson(attributes.data, MarketplaceInvoiceData.Redirect::class.java)
            else -> throw IllegalArgumentException("Unknown marketplace invoice data type '${attributes.type}'")
        }
    }
}