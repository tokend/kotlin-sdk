package org.tokend.sdk.api.integrations.invitations

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.invitations.model.CreateInvitationRequest
import org.tokend.sdk.api.integrations.invitations.model.generated.resources.EventResource
import org.tokend.sdk.api.integrations.invitations.model.generated.resources.InfoResource
import org.tokend.sdk.api.integrations.invitations.model.generated.resources.InvitationResource
import org.tokend.sdk.api.integrations.invitations.params.InvitationHistoryPageParams
import org.tokend.sdk.api.integrations.invitations.params.InvitationsPageParams
import org.tokend.sdk.api.integrations.invitations.params.SortedInvitationsPageParams
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.signing.SignInterceptor

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
                url = "integrations/invitations/$id",
                responseClass = InvitationResource::class.java
        )
    }

    @JvmOverloads
    fun getPage(params: InvitationsPageParams? = null): ApiRequest<DataPage<InvitationResource>> {
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
                url = "integrations/invitations/$id",
                responseClass = Void::class.java
        )
    }

    fun acceptInvitation(id: String): ApiRequest<Void> {
        return customRequestsApi.patch(
                url = "integrations/invitations/$id/accept",
                body = Any(),
                responseClass = Void::class.java
        )
    }

    fun cancelInvitation(id: String): ApiRequest<Void> {
        return customRequestsApi.patch(
                url = "integrations/invitations/$id/cancel",
                body = Any(),
                responseClass = Void::class.java
        )
    }

    fun redeemInvitation(id: String): ApiRequest<Void> {
        return customRequestsApi.patch(
                url = "integrations/invitations/$id/redeem",
                body = Any(),
                responseClass = Void::class.java
        )
    }

    fun redeemNotOwnedInvitation(id: String,
                                 signature: String): ApiRequest<Void> {
        return customRequestsApi.patch(
                url = "integrations/invitations/$id/redeem",
                body = Any(),
                headersMap = mapOf(SignInterceptor.AUTH_HEADER to signature),
                responseClass = Void::class.java
        )
    }

    fun getRedemptionSignature(id: String,
                               requestSigner: RequestSigner): String {
        val relativeUrl = "/integrations/invitations/$id/redeem"
        val signatureHeaders = SignInterceptor.getSignatureHeaders(
                signer = requestSigner,
                method = "PATCH",
                relativeUrl = relativeUrl
        )
        return signatureHeaders.getValue(SignInterceptor.AUTH_HEADER)
    }

    fun waitInvitation(id: String): ApiRequest<Void> {
        return customRequestsApi.patch(
                url = "integrations/invitations/$id/wait",
                body = Any(),
                responseClass = Void::class.java
        )
    }

    fun getHistoryPage(params: InvitationHistoryPageParams? = null):
            ApiRequest<DataPage<EventResource>> {
        return customRequestsApi.getPage(
                url = "integrations/invitations/history",
                pageItemClass = EventResource::class.java,
                queryMap = params.map()
        )
    }
}