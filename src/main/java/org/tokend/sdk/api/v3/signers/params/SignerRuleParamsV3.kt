package org.tokend.sdk.api.v3.signers.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see SignerRuleParamsV3.Includes
 */
class SignerRuleParamsV3(
        include: Collection<String>
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val RULES = "rules"
            const val OWNER = "owner"
        }
    }
}