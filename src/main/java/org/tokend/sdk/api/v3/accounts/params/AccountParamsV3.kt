package org.tokend.sdk.api.v3.accounts.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see AccountParamsV3.Includes
 */
open class AccountParamsV3(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val FEES = "fees"
            const val BALANCES = "balances"
            const val BALANCES_ASSET = "balances.asset"
            const val BALANCES_STATE = "balances.state"
            const val REFERRER = "referrer"
            const val LIMITS = "limits"
            const val EXTERNAL_SYSTEM_IDS = "external_system_ids"
        }
    }
}