package org.tokend.sdk.api.integrations.escrow

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.integrations.escrow.params.CreateEscrowParams
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceData
import org.tokend.sdk.utils.BigDecimalUtil

open class EscrowApi(
        protected open val escrowService: EscrowService
) {
    open fun create(params: CreateEscrowParams): ApiRequest<MarketplaceInvoiceData.Redirect> {
        val body = mapOf(
                "type" to "escrows",
                "attributes" to mapOf(
                        "amount" to BigDecimalUtil.toPlainString(params.amount),
                        "asset" to params.asset,
                        "subject" to params.subject,
                        "source" to params.sourceAccount,
                        "source_email" to params.sourceEmail,
                        "destination" to params.destAccount,
                        "destination_email" to params.destEmail
                ),
                "relationships" to mapOf(
                        "payment_method" to DataEntity(
                                mapOf(
                                        "id" to params.paymentMethodId,
                                        "type" to "payment-methods"
                                )
                        )
                )
        )

        return MappedRetrofitApiRequest(
                escrowService.createEscrow(DataEntity(body)),
                { it.data.attributes.invoice }
        )
    }
}