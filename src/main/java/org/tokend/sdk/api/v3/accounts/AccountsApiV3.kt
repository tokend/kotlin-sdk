package org.tokend.sdk.api.v3.accounts

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.AccountResource
import org.tokend.sdk.api.generated.resources.BalanceResource
import org.tokend.sdk.api.v3.accounts.params.AccountParamsV3
import org.tokend.sdk.api.v3.accounts.params.AccountsPageParamsV3

open class AccountsApiV3(
        protected open val accountsService: AccountsServiceV3
) {
    /**
     * @return accounts list page
     *
     * Should be signed by signer of requested account or signer of master account
     *
     */
    open fun get(params: AccountsPageParamsV3?): ApiRequest<DataPage<AccountResource>> {
        return MappedRetrofitApiRequest(
                accountsService.getAccounts(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return account by it's ID
     *
     * Should be signed by signer of requested account or signer of master account
     */
    open fun getById(accountId: String,
                     params: AccountParamsV3?): ApiRequest<AccountResource> {
        return MappedRetrofitApiRequest(
                accountsService.getAccountById(
                        accountId,
                        params.map()
                ),
                JSONAPIDocument<AccountResource>::get
        )
    }

    /**
     * @return balances of an account with given ID
     *
     * Should be signed by signer of requested account or signer of master account,
     * otherwise will be populated only with id and asset
     */
    open fun getBalances(accountId: String): ApiRequest<List<BalanceResource>> {
        return getById(accountId, AccountParamsV3(listOf(AccountParamsV3.Includes.BALANCES)))
                .map { it.balances }
    }
}