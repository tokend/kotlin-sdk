package org.tokend.sdk.api.ingester.accounts.params

import org.tokend.sdk.api.ingester.accounts.params.IngesterAccountParams.Includes
import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see Includes
 */
open class IngesterAccountParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val REFERRER = "referrer"
            const val ROLE = "role"
            const val ROLE_RULES = "role.rules"
            const val KYC_DATA = "kyc_data"
            const val BALANCES = "balances"
            const val BALANCE_ASSETS = "balances.asset"
            const val BALANCE_STATES = "balances.state"
        }
    }
}