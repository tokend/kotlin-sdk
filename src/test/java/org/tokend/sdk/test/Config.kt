package org.tokend.sdk.test

import org.tokend.wallet.Account

object Config {
    const val API_URL = "https://api.angus.tokend.io/"
    const val ADMIN_SEED = "SAMJKTZVW5UOHCDK5INYJNORF2HRKYI72M5XSZCBYAHQHR34FFR4Z6G4"
    val ADMIN_ACCOUNT = Account.fromSecretSeed(ADMIN_SEED.toCharArray())
}