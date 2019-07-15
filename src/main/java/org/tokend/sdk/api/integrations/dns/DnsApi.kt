package org.tokend.sdk.api.integrations.dns

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.integrations.dns.model.BusinessResource
import org.tokend.sdk.api.integrations.dns.model.ClientResource
import org.tokend.sdk.api.integrations.dns.model.ClientToInviteResource
import org.tokend.sdk.api.integrations.dns.params.ClientsPageParams

open class DnsApi(
        protected open val dnsService: DnsService
) {
    @JvmOverloads
    open fun getBusinessClients(businessId: String,
                                params: ClientsPageParams? = null): ApiRequest<DataPage<ClientResource>> {
        return MappedRetrofitApiRequest(
                dnsService.getBusinessClients(
                        businessId,
                        params.map()
                ),
                DataPage.Companion::fromPageDocument
        )
    }

    @JvmOverloads
    open fun getClientBusinesses(clientId: String,
                                 params: ClientsPageParams? = null): ApiRequest<DataPage<BusinessResource>> {
        return MappedRetrofitApiRequest(
                dnsService.getClientBusinesses(
                        clientId,
                        params.map()
                ),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun inviteClients(businessId: String,
                           emails: List<String>): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                dnsService.inviteClients(
                        businessId,
                        JSONAPIDocument(emails.map { ClientToInviteResource(it) })
                )
        )
    }
}