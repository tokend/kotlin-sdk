package org.tokend.sdk.federation

open class ForbiddenException(val type: String, detailMessage: String) : Exception(detailMessage)

class NeedTfaException(val backendId: Int,
                       val backendType: String,
                       val token: String,
                       val keychainData: String,
                       val salt: String,
                       val walletId: String)
    : ForbiddenException("tfa_required", "Need TFA on $backendId") {

    companion object {
        var TOKEN = "token"
        var BACKEND_ID = "factor_id"
        var BACKEND_TYPE = "factor_type"
        var KEYCHAIN_DATA = "keychain_data"
        var SALT = "salt"
        var WALLET_ID = "wallet_id"
    }
}

class NotFoundException : RuntimeException {
    constructor(message: String) : super(message) {}

    constructor() {}

    constructor(ex: Exception) : super(ex) {}
}