package org.tokend.sdk.keyserver.models

data class WalletInfo(var accountId: String,
                      var email: String,
                      var walletIdHex: String,
                      var secretSeed: CharArray,
                      var loginParams: LoginParamsResponse)