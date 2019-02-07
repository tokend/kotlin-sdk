package org.tokend.sdk.api.v2.accounts.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams

/**
 * @see AccountParamsV2.Includes
 */
open class AccountParamsV2(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val ROLE = "role"
            const val BALANCES = "balances"
            const val REFERRER = "referrer"
        }
    }
}