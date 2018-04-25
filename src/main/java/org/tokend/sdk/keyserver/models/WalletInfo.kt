package org.tokend.sdk.keyserver.models

data class WalletInfo(var accountId: String,
                      var walletIdHex: String,
                      var secretSeed: String)