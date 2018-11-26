package org.tokend.sdk.api.authenticator

import org.tokend.sdk.api.authenticator.model.AuthResult
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataEntity

open class AuthResultsApi(
        protected val authResultsService: AuthResultsService
) {
    open fun getAuthResult(publicKey: String): ApiRequest<AuthResult> {
        return MappedRetrofitApiRequest(
                authResultsService.getAuthResult(publicKey),
                { it.data }
        )
    }

    open fun postAuthResult(publicKey: String,
                            result: AuthResult): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                authResultsService.postAuthResult(
                        publicKey,
                        DataEntity(result)
                )
        )
    }
}