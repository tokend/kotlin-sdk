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
            const val ROLE = "role"
            const val ROLE_RULES = "role.rules"
            const val BALANCES = "balances"
            const val BALANCES_ASSET = "balances.asset"
            const val BALANCES_STATE = "balances.state"
            const val REFERRER = "referrer"
            const val STATE = "state"
        }
    }
}