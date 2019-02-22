package org.tokend.sdk.test

import org.tokend.wallet.Account

object Config {
    const val API_URL = "https://api.anubis.tokend.io/"
    const val ADMIN_SEED = "SDIZ6JIMRDHBUSWMS4PPD7BKHXSYM3SXCIKTDAH3X2DCHYJ4MYL3TVSL"
    val ADMIN_ACCOUNT = Account.fromSecretSeed(ADMIN_SEED.toCharArray())
}