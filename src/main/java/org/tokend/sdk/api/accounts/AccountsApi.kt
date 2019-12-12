package org.tokend.sdk.api.accounts

import org.tokend.sdk.api.accounts.model.CreateAccountRequestBody
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.keyserver.models.SignerData

open class AccountsApi(
        protected open val accountsService: AccountsService
) {
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