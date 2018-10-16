package org.tokend.sdk.api.wallets.params

import org.tokend.sdk.api.base.params.QueryParams

open class KdfRequestParams(
        val email: String? = null,
        val isRecovery: Boolean? = null
) : QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>().apply {
            email?.also { put("email", it) }
            isRecovery?.also { put("is_recovery", it) }
        }
    }
}