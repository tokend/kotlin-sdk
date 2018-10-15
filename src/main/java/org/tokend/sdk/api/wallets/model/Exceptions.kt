package org.tokend.sdk.api.wallets.model

import org.tokend.sdk.api.base.model.ForbiddenException

open class InvalidCredentialsException(val credential: Credential) : Exception() {
    enum class Credential {
        EMAIL, PASSWORD
    }
}

open class EmailNotVerifiedException(val walletId: String)
    : ForbiddenException("email_not_verified", "Email is not verified")

open class EmailAlreadyTakenException : Exception()