package org.tokend.sdk.api.integrations.invoices

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.NameValue
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.invoices.model.CreateInvoiceRequest
import org.tokend.sdk.api.integrations.invoices.model.InvoiceState
import org.tokend.sdk.api.integrations.invoices.model.generated.resources.InvoiceResource
import org.tokend.sdk.api.integrations.invoices.params.InvoicesPageParams
import org.tokend.sdk.api.v3.base.JsonApiQueryParams

open class InvoicesApi(
        protected open val customRequestsApi: CustomRequestsApi
) {
    open fun getInvoices(params: InvoicesPageParams? = null) =
            customRequestsApi.getPage(
                    url = "integrations/invoices",
                    pageItemClass = InvoiceResource::class.java,
                    queryMap = params.map()
            )

    open fun getInvoice(id: String,
                        params: JsonApiQueryParams? = null) =
            customRequestsApi.get(
                    url = "integrations/invoices/$id",
                    responseClass = InvoiceResource::class.java,
                    queryMap = params.map()
            )

    open fun createInvoice(request: CreateInvoiceRequest) =
            customRequestsApi.post(
                    url = "integrations/invoices",
                    body = DataEntity(request),
                    responseClass = InvoiceResource::class.java
            )

    open fun updateInvoiceState(id: String,
                                newState: InvoiceState) =
            customRequestsApi.patch(
                    url = "integrations/invoices/$id",
                    body = DataEntity(AttributesEntity(mapOf(
                            "state" to NameValue(newState.name.toLowerCase(), newState.value)
                    ))),
                    responseClass = Void::class.java
            )
}