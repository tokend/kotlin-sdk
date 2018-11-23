package org.tokend.sdk.api.requests.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.model.base.RequestState
import org.tokend.wallet.xdr.AccountType

/**
 * Query params for KYC update reviewable requests request.
 * @see <a href="https://tokend.gitlab.io/docs/#get-update-kyc-requests">Docs</a>
 */
class KycUpdateRequestsParams(
        pagingParams: PagingParams? = null,
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        updatedAfterTimestamp: Long? = null,
        val accountToUpdateKyc: String? = null,
        val accountTypeToSet: AccountType? = null,
        val maskSet: Int? = null,
        val maskSetPartEq: Int? = null,
        val maskNotSet: Int? = null
) : RequestsParams(pagingParams, reviewer, requestor, state, updatedAfterTimestamp) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            accountToUpdateKyc?.also { put("account_to_update_kyc", it) }
            accountTypeToSet?.also { put("account_type_to_set", it.value) }
            maskSet?.also { put("mask_set", it) }
            maskSetPartEq?.also { put("mask_set_part_eq", it) }
            maskNotSet?.also { put("mask_not_set", it) }
        }
    }
}