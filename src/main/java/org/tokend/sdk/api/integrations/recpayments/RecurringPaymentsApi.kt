package org.tokend.sdk.api.integrations.recpayments

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.recpayments.model.SchedulePaymentRequest
import org.tokend.sdk.api.integrations.recpayments.model.generated.resources.ScheduledPaymentRecordResource
import org.tokend.sdk.api.integrations.recpayments.params.ScheduledPaymentsPageParams

open class RecurringPaymentsApi(
        protected open val customRequestsApi: CustomRequestsApi
) {
    open fun getScheduledPayments(params: ScheduledPaymentsPageParams? = null
    ): ApiRequest<DataPage<ScheduledPaymentRecordResource>> {
        return customRequestsApi.getPage(
                url = "integrations/rpayments/schedule",
                pageItemClass = ScheduledPaymentRecordResource::class.java,
                queryMap = params.map()
        )
    }

    open fun getScheduledPayment(id: String): ApiRequest<ScheduledPaymentRecordResource> {
        return customRequestsApi.get(
                url = "integrations/rpayments/schedule/$id",
                responseClass = ScheduledPaymentRecordResource::class.java
        )
    }

    open fun schedulePayment(request: SchedulePaymentRequest): ApiRequest<ScheduledPaymentRecordResource> {
        return customRequestsApi.post(
                url = "integrations/rpayments/schedule",
                body = DataEntity(request),
                responseClass = ScheduledPaymentRecordResource::class.java
        )
    }

    open fun deleteScheduledPayment(id: String): ApiRequest<Void> {
        return customRequestsApi.delete(
                url = "integrations/rpayments/schedule/$id",
                responseClass = Void::class.java
        )
    }
}