package org.tokend.sdk.api.accounts

import org.tokend.sdk.api.accounts.model.AccountsDetailsResponse
import org.tokend.sdk.api.accounts.params.OffersParams
import org.tokend.sdk.api.accounts.params.PaymentsParams
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.models.Offer
import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.api.models.SimpleBalanceDetails
import org.tokend.sdk.api.models.transactions.Transaction
import org.tokend.sdk.api.requests.models.AccountsDetailsRequestBody
import org.tokend.sdk.api.responses.AccountResponse
import org.tokend.sdk.utils.PaymentRecordConverter

open class AccountsApi(
        protected open val accountsService: AccountsService
) {
    fun get(accountId: String): ApiRequest<AccountResponse> {
        return SimpleRetrofitApiRequest(
                accountsService.getAccount(accountId)
        )
    }

    fun getSigners(accountId: String): ApiRequest<List<AccountResponse.Signer>> {
        return MappedRetrofitApiRequest(
                accountsService.getSigners(accountId),
                { accountResponse ->
                    accountResponse.signers
                }
        )
    }

    fun getBalances(accountId: String): ApiRequest<List<AccountResponse.Balance>> {
        return SimpleRetrofitApiRequest(
                accountsService.getBalances(accountId)
        )
    }

    fun getBalancesDetails(accountId: String): ApiRequest<List<SimpleBalanceDetails>> {
        return SimpleRetrofitApiRequest(
                accountsService.getBalancesDetails(accountId)
        )
    }

    fun getPayments(accountId: String,
                    paymentsParams: PaymentsParams? = null
    ): ApiRequest<DataPage<PaymentRecord>> {
        return MappedRetrofitApiRequest(
                accountsService.getPayments(
                        accountId,
                        paymentsParams?.map()
                ),
                { DataPage.fromPage(it) }
        )
    }

    fun getPaymentTransactions(accountId: String,
                               paymentsParams: PaymentsParams
    ): ApiRequest<DataPage<Transaction>> {
        val contextAsset = paymentsParams.asset
                ?: throw IllegalArgumentException(
                        "To convert payment records to transactions " +
                                "'asset' param is required"
                )

        return MappedRetrofitApiRequest(
                accountsService.getPayments(
                        accountId,
                        paymentsParams.map()
                ),
                { page ->
                    val items = page.records
                    val isLast = DataPage.isLast(page)
                    val nextCursor = DataPage.getNextCursor(page)

                    val transactions = PaymentRecordConverter
                            .toTransactions(items, accountId, contextAsset)

                    DataPage(nextCursor, transactions, isLast)
                }
        )
    }

    fun getPendingOffers(accountId: String,
                         offersParams: OffersParams? = null): ApiRequest<List<Offer>> {
        return MappedRetrofitApiRequest(
                accountsService.getPendingOffers(
                        accountId,
                        offersParams?.map()
                ),
                { it.records }
        )
    }

    fun getDetails(accountIds: Collection<String>): ApiRequest<AccountsDetailsResponse> {
        return SimpleRetrofitApiRequest(
                accountsService.getAccountsDetails(
                        AccountsDetailsRequestBody(accountIds)
                )
        )
    }
}