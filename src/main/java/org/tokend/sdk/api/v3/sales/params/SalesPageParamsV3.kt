package org.tokend.sdk.api.v3.sales.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.sales.model.SaleState
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.sdk.utils.ApiDateUtil
import org.tokend.sdk.utils.BigDecimalUtil
import org.tokend.wallet.xdr.SaleType
import java.math.BigDecimal
import java.util.*

/**
 * @see SaleParamsV3.Includes
 * @see SaleStates
 */

class SalesPageParamsV3(
        val owner: String? = null,
        val minStartTime: Date? = null,
        val minEndTime: Date? = null,
        val maxStartTime: Date? = null,
        val maxEndTime: Date? = null,
        val state: SaleState? = null,
        val maxSoftCap: BigDecimal? = null,
        val maxHardCap: BigDecimal? = null,
        val minSoftCap: BigDecimal? = null,
        val minHardCap: BigDecimal? = null,
        val baseAsset: String? = null,
        val saleType: SaleType? = null,
        includes: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, includes) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            owner?.also { putFilter("owner", it) }
            minStartTime?.also { putFilter("min_start_time", ApiDateUtil.formatDateForRequest(it)) }
            minEndTime?.also { putFilter("min_end_time", ApiDateUtil.formatDateForRequest(it)) }
            maxStartTime?.also { putFilter("max_start_time", ApiDateUtil.formatDateForRequest(it)) }
            maxEndTime?.also { putFilter("max_end_time", ApiDateUtil.formatDateForRequest(it)) }
            state?.also { putFilter("state", it.value) }
            maxSoftCap?.also { putFilter("max_soft_cap", BigDecimalUtil.toPlainString(it)) }
            maxHardCap?.also { putFilter("max_hard_cap", BigDecimalUtil.toPlainString(it)) }
            minSoftCap?.also { putFilter("min_soft_cap", BigDecimalUtil.toPlainString(it)) }
            minHardCap?.also { putFilter("min_hard_cap", BigDecimalUtil.toPlainString(it)) }
            maxSoftCap?.also { putFilter("max_soft_cap", BigDecimalUtil.toPlainString(it)) }
            baseAsset?.also { putFilter("base_asset", it) }
            saleType?.also { putFilter("sale_type", it.value) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var owner: String? = null
        private var minStartTime: Date? = null
        private var minEndTime: Date? = null
        private var maxStartTime: Date? = null
        private var maxEndTime: Date? = null
        private var state: SaleState? = null
        private var maxSoftCap: BigDecimal? = null
        private var maxHardCap: BigDecimal? = null
        private var minSoftCap: BigDecimal? = null
        private var minHardCap: BigDecimal? = null
        private var baseAsset: String? = null
        private var saleType: SaleType? = null

        fun withOwner(owner: String) = also { this.owner = owner }

        fun withMinStartTime(minStartTime: Date) = also { this.minStartTime = minStartTime }

        fun withMinEndTime(minEndTime: Date) = also { this.minEndTime = minEndTime }

        fun withMaxStartTime(maxStartTime: Date) = also { this.maxStartTime = maxStartTime }

        fun withMaxEndTime(maxEndTime: Date) = also { this.maxEndTime = maxEndTime }

        fun withState(state: SaleState) = also { this.state = state }

        fun withMaxSoftCap(maxSoftCap: BigDecimal) = also { this.maxSoftCap = maxSoftCap }

        fun withMaxHardCap(maxHardCap: BigDecimal) = also { this.maxHardCap = maxHardCap }

        fun withMinSoftCap(minSoftCap: BigDecimal) = also { this.minSoftCap = minSoftCap }

        fun withMinHardCap(minHardCap: BigDecimal) = also { this.minHardCap = minHardCap }

        fun withBaseAsset(baseAsset: String) = also { this.baseAsset = baseAsset }

        fun withSaleType(saleType: SaleType) = also { this.saleType = saleType }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): SalesPageParamsV3 {
            return SalesPageParamsV3(owner, minStartTime, minEndTime, maxStartTime, maxEndTime,
                    state, maxSoftCap, maxHardCap, minSoftCap, minHardCap, baseAsset, saleType,
                    include, pagingParams)
        }
    }
}