package org.tokend.sdk.federation

import java.io.IOException

open class ForbiddenException(val type: String, detailMessage: String) : IOException(detailMessage)

open class InvalidCredentialsException(val credential: Credential) : Exception() {
    enum class Credential {
        EMAIL, PASSWORD
    }
}
open class EmailNotVerifiedException(val walletId: String)
    : ForbiddenException("email_not_verified", "Email is not verified")
open class EmailAlreadyTakenException : Exception()