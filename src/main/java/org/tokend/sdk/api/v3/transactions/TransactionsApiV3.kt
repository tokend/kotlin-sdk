package org.tokend.sdk.api.v3.transactions

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.TransactionResource
import org.tokend.sdk.api.v3.transactions.params.TransactionsPageParams

open class TransactionsApiV3(
        protected val transactionsService: TransactionsServiceV3
) {
    /**
     * @return transactions list page
     */
    open fun get(params: TransactionsPageParams? = null): ApiRequest<DataPage<TransactionResource>> {
        return MappedRetrofitApiRequest(
                transactionsService.getTransactions(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return transaction by it's ID
     */
    open fun getById(id: String): ApiRequest<TransactionResource> {
        return MappedRetrofitApiRequest(
                transactionsService.getTransactionById(
                        id,
                        emptyMap()
                ),
                JSONAPIDocument<TransactionResource>::get
        )
    }
}