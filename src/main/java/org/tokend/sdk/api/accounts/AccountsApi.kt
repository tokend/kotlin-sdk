package org.tokend.sdk.api.accounts

import org.tokend.sdk.api.accounts.model.*
import org.tokend.sdk.api.accounts.params.OffersParams
import org.tokend.sdk.api.accounts.params.PaymentsParams
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.model.transactions.Transaction
import org.tokend.sdk.api.trades.model.Offer
import org.tokend.sdk.utils.PaymentRecordConverter

open class AccountsApi(
        protected open val accountsService: AccountsService
) {
    /**
     * Will return specific account by ID.
     * See <a href="https://tokend.gitlab.io/docs/?kotlin#get-account-by-id">Docs</a>
     * */
    open fun getById(accountId: String): ApiRequest<Account> {
        return SimpleRetrofitApiRequest(
                accountsService.getAccount(accountId)
        )
    }

    /**
     * Will return information about all account signers.
     * See <a href="https://tokend.gitlab.io/docs/?http#get-account-signers">Docs</a>
     */
    open fun getSigners(accountId: String): ApiRequest<List<Account.Signer>> {
        return MappedRetrofitApiRequest(
                accountsService.getSigners(accountId),
                { accountResponse ->
                    accountResponse.signers
                }
        )
    }

    /**
     * Will return account balances.
     * See <a href="https://tokend.gitlab.io/docs/?http#get-account-balances">Docs</a>
     */
    open fun getBalances(accountId: String): ApiRequest<List<Account.Balance>> {
        return SimpleRetrofitApiRequest(
                accountsService.getBalances(accountId)
        )
    }

    /**
     * Will return extended account balances details.
     * Includes asset details for every balance and sales created for this asset, if present.
     * See <a href="https://tokend.gitlab.io/docs/?http#get-account-balances-details">Docs</a>
     */
    open fun getBalancesDetails(accountId: String): ApiRequest<List<SimpleBalanceDetails>> {
        return SimpleRetrofitApiRequest(
                accountsService.getBalancesDetails(accountId)
        )
    }

    /**
     * Will return list of account payments.
     * See <a href="https://tokend.gitlab.io/docs/?http#get-account-payments">Docs</a>
     */
    open fun getPayments(accountId: String,
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

    /**
     * Will return list of account transactions.
     * See <a href="https://tokend.gitlab.io/docs/?http#get-account-transactions">Docs</a>
     */
    open fun getPaymentTransactions(accountId: String,
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

    /**
     * Will return list of pending offers for specified account.
     * See <a href="https://tokend.gitlab.io/docs/?http#get-account-pending-offers">Docs</a>
     */
    open fun getPendingOffers(accountId: String,
                              offersParams: OffersParams? = null): ApiRequest<DataPage<Offer>> {
        return MappedRetrofitApiRequest(
                accountsService.getPendingOffers(
                        accountId,
                        offersParams?.map()
                ),
                { DataPage.fromPage(it) }
        )
    }

    open fun getDetails(accountIds: Collection<String>): ApiRequest<AccountsDetailsResponse> {
        return SimpleRetrofitApiRequest(
                accountsService.getAccountsDetails(
                        AccountsDetailsRequestBody(accountIds)
                )
        )
    }

    open fun getAccountIdByEmail(email: String): ApiRequest<String> {
        return MappedRetrofitApiRequest(
                accountsService.getAccountIdByEmail(email),
                { it.accountId }
        )
    }
}