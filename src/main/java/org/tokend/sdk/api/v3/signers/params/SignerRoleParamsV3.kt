package org.tokend.sdk.api.v3.signers.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

open class SignerRoleParamsV3(
        include: Collection<String>
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val SIGNER_RULES = "rules"
            const val OWNER = "owner"
        }
    }
}