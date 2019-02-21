package org.tokend.sdk.api.requests

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.requests.model.SimpleReviewableRequest
import org.tokend.sdk.api.requests.model.aml.AmlAlertReviewableRequest
import org.tokend.sdk.api.requests.model.asset.SimpleAssetReviewableRequest
import org.tokend.sdk.api.requests.model.issuance.IssuanceReviewableRequest
import org.tokend.sdk.api.requests.model.limits.LimitsUpdateReviewableRequest
import org.tokend.sdk.api.requests.model.preissuance.PreIssuanceReviewableRequest
import org.tokend.sdk.api.requests.model.sale.SimpleSaleReviewableRequest
import org.tokend.sdk.api.requests.model.sale.details.SaleDetailsUpdateReviewableRequest
import org.tokend.sdk.api.requests.model.sale.endtime.SaleEndTimeUpdateReviewableRequest
import org.tokend.sdk.api.requests.model.withdrawal.WithdrawalReviewableRequest
import org.tokend.sdk.api.requests.params.*

open class RequestsApi(
        protected val requestsService: RequestsService
) {
    /**
     * Will return list of all reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-requests-history">Docs</a>
     */
    open fun getAll(params: RequestsParams?): ApiRequest<DataPage<SimpleReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getAll(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of asset-related reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-assets-requests">Docs</a>
     */
    open fun getAssets(params: AssetRequestsParams?): ApiRequest<DataPage<SimpleAssetReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getAssets(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of sales-related reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-sale-creation-requests">Docs</a>
     */
    open fun getSales(params: SaleRequestsParams?): ApiRequest<DataPage<SimpleSaleReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getSales(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of pre-issuance reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-pre-issuance-requests">Docs</a>
     */
    open fun getPreIssuances(params: PreIssuanceRequestsParams?)
            : ApiRequest<DataPage<PreIssuanceReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getPreIssuances(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of issuance reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-issuance-requests">Docs</a>
     */
    open fun getIssuances(params: IssuanceRequestsParams?)
            : ApiRequest<DataPage<IssuanceReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getIssuances(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of issuance reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-withdrawals-requests">Docs</a>
     */
    open fun getWithdrawals(params: WithdrawalRequestsParams?)
            : ApiRequest<DataPage<WithdrawalReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getWithdrawals(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of AML alert reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-aml-alerts-requests">Docs</a>
     */
    open fun getAmlAlerts(params: AmlAlertRequestsParams?)
            : ApiRequest<DataPage<AmlAlertReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getAmlAlerts(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of limits update reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-limits-updates-requests">Docs</a>
     */
    open fun getLimitsUpdates(params: LimitsUpdateRequestsParams?)
            : ApiRequest<DataPage<LimitsUpdateReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getLimitsUpdates(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of sale details update reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-update-sale-details-requests">Docs</a>
     */
    open fun getSaleDetailsUpdates(params: SaleDetailsUpdateRequestsParams?)
            : ApiRequest<DataPage<SaleDetailsUpdateReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getSaleDetailsUpdates(params.map()),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of sale end time update reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-update-sale-details-requests">Docs</a>
     */
    open fun getSaleEndTimeUpdates(params: SaleEndTimeUpdateRequestsParams?)
            : ApiRequest<DataPage<SaleEndTimeUpdateReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getSaleEndTimeUpdates(params.map()),
                { DataPage.fromPage(it) }
        )
    }
}