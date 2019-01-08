package org.tokend.sdk.api.v2.requests.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.requests.model.base.RequestState
import org.tokend.wallet.xdr.AccountType
import org.tokend.wallet.xdr.ReviewableRequestType

open class KYCRequestPageParams(
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        type: ReviewableRequestType? = null,
        updatedAfter: Long? = null,
        includes: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null,
        val accountToUpdateKYC: String? = null,
        val accountTypeToSet: AccountType? = null,
        val maskSet: Int? = null,
        val maskSetPartEq: Int? = null,
        val maskNotSet: Int? = null
) : RequestsPageParamsV2(
        reviewer,
        requestor,
        state,
        type,
        updatedAfter,
        includes,
        pagingParams) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            accountToUpdateKYC?.also { put("account_to_update_kyc", it) }
            accountTypeToSet?.also { put("account_type_to_set", it) }
            maskSet?.also { put("mask_set", it) }
            maskSetPartEq?.also { put("mask_set_part_eq", it) }
            maskNotSet?.also { put("mask_not_set", it) }
        }
    }
}