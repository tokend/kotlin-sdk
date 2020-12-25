package org.tokend.sdk.api.accounts

import org.tokend.sdk.api.accounts.model.CreateAccountRequestBody
import org.tokend.sdk.api.accounts.model.limits.Limits
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.keyserver.models.SignerData

open class AccountsApi(
        protected open val accountsService: AccountsService
) {
    /**
     * Will return limits and statistics of given account.
     *
     * @see <a href="https://tokend.gitlab.io/docs/#get-account-limits">Docs</a>
     *
     * @see org.tokend.sdk.api.v3.accounts.AccountsApiV3.getLimits
     */
    @Deprecated("We are going to replace with AccountsApiV3.getLimits")
    open fun getLimits(accountId: String): ApiRequest<Limits> {
        return MappedRetrofitApiRequest(
                accountsService.getLimits(accountId),
                { Limits(it.entries ?: emptyList()) }
        )
    }

    /**
     * Creates an account with specified signers.
     */
    open fun createAccount(accountId: String,
                           signers: Collection<SignerData>): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                accountsService.createAccount(CreateAccountRequestBody(accountId, signers))
        )
    }
}