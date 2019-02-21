package org.tokend.sdk.api.v3.accounts.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see AccountRoleParamsV3.Includes
 */
class AccountRoleParamsV3(
        include: Collection<String>
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val ACCOUNT_RULE = "rules"
        }
    }
}