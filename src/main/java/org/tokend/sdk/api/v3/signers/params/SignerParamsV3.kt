package org.tokend.sdk.api.v3.signers.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

open class SignerParamsV3(
        include: Collection<String>
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val SIGNER_ROLE = "role"
            const val SIGNER_ROLE_RULES = "role.rules"
        }
    }
}