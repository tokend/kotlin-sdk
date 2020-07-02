package org.tokend.sdk.api.integrations.invitations

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.invitations.model.CreateInvitationRequest
import org.tokend.sdk.api.integrations.invitations.model.generated.resources.InfoResource
import org.tokend.sdk.api.integrations.invitations.model.generated.resources.InvitationResource
import org.tokend.sdk.api.integrations.invitations.params.SortedInvitationsPageParams
import org.tokend.sdk.api.integrations.invoices.params.InvoicesPageParams

open class InvitationsApi(
        protected open val customRequestsApi: CustomRequestsApi
) {
    fun getSystemInfo(): ApiRequest<InfoResource> {
        return customRequestsApi.get(
                url = "integrations/invitations/info",
                responseClass = InfoResource::class.java
        )
    }

    fun getInvitation(id: String): ApiRequest<InvitationResource> {
        return customRequestsApi.get(
                url = "integrations/invoices/$id",
                responseClass = InvitationResource::class.java
        )
    }

    @JvmOverloads
    fun getPage(params: InvoicesPageParams? = null): ApiRequest<DataPage<InvitationResource>> {
        return customRequestsApi.getPage(
                url = "integrations/invitations",
                pageItemClass = InvitationResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    fun getSortedPage(params: SortedInvitationsPageParams? = null
    ): ApiRequest<DataPage<InvitationResource>> {
        return customRequestsApi.getPage(
                url = "integrations/invitations/sorted",
                pageItemClass = InvitationResource::class.java,
                queryMap = params.map()
        )
    }

    fun createInvitation(request: CreateInvitationRequest): ApiRequest<InvitationResource> {
        return customRequestsApi.post(
                url = "integrations/invitations",
                body = DataEntity(request),
                responseClass = InvitationResource::class.java
        )
    }

    fun deleteInvitation(id: String): ApiRequest<Void> {
        return customRequestsApi.delete(
                url = "integrations/invoices/$id",
                responseClass = Void::class.java
        )
    }

    fun acceptInvitation(id: String): ApiRequest<Void> {
        return customRequestsApi.patch(
                url = "integrations/invoices/$id/accept",
                body = Any(),
                responseClass = Void::class.java
        )
    }

    fun cancelInvitation(id: String): ApiRequest<Void> {
        return customRequestsApi.patch(
                url = "integrations/invoices/$id/cancel",
                body = Any(),
                responseClass = Void::class.java
        )
    }

    fun redeemInvitation(id: String): ApiRequest<Void> {
        return customRequestsApi.patch(
                url = "integrations/invoices/$id/redeem",
                body = Any(),
                responseClass = Void::class.java
        )
    }

    fun waitInvitation(id: String): ApiRequest<Void> {
        return customRequestsApi.patch(
                url = "integrations/invoices/$id/wait",
                body = Any(),
                responseClass = Void::class.java
        )
    }
}