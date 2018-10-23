package org.tokend.sdk.api.base.params

import java.util.*
import org.tokend.sdk.api.accounts.params.PaymentsParams

/**
 * Base query params for all ledger index requests.
 *
 * @see <a href="https://tokend.gitlab.io/docs/#ledger-index">Ledger index docs</a>
 */
class OperationsParams(
        val since: Date? = null,
        val to: Date? = null,
        val completedOnly: Boolean? = null,
        val skipCanceled: Boolean? = null,
        val pendingOnly: Boolean? = null,
        val accountType: Boolean? = null,
        val accountId: String? = null
) : QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>()
                .apply {
                    since?.also { put("since", it) }
                    to?.also { put("to", it) }
                    completedOnly?.also { put("completed_only", it) }
                    skipCanceled?.also { put("skip_canceled", it) }
                    pendingOnly?.also { put("pending_only", it) }
                    accountType?.also { put("account_type", it) }
                    accountId?.also { put("account_id", it) }
                }
    }
}