package org.tokend.sdk.api.v3.requests.params

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
) : RequestsPageParamsV3(
        reviewer,
        requestor,
        state,
        type,
        updatedAfter,
        includes,
        pagingParams) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            accountToUpdateKYC?.also { putFilter("account_to_update_kyc", it) }
            accountTypeToSet?.also { putFilter("account_type_to_set", it) }
            maskSet?.also { putFilter("mask_set", it) }
            maskSetPartEq?.also { putFilter("mask_set_part_eq", it) }
            maskNotSet?.also { putFilter("mask_not_set", it) }
        }
    }

    class Builder : RequestsPageParamsV3.Builder() {
        private var accountToUpdateKYC: String? = null
        private var accountTypeToSet: AccountType? = null
        private var maskSet: Int? = null
        private var maskSetPartEq: Int? = null
        private var maskNotSet: Int? = null

        fun withAccountToUpdateKYC(account: String) = also { this.accountToUpdateKYC = account }

        fun withAccountTypeToSet(accountType: AccountType) = also { this.accountTypeToSet = accountType }

        fun withMaskSet(mask: Int) = also { this.maskSet = mask }

        fun withMaskSetPartEq(mask: Int) = also { this.maskSetPartEq = mask }

        fun withMaskNotSet(mask: Int) = also { this.maskNotSet = mask }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): KYCRequestPageParams {
            return KYCRequestPageParams(reviewer, requestor, state, type, updatedAfter, include,
                    pagingParams, accountToUpdateKYC, accountTypeToSet, maskSet, maskSetPartEq, maskNotSet)
        }
    }
}