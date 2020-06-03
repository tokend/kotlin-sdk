package org.tokend.sdk.api.integrations.dns

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.integrations.dns.model.BusinessResource
import org.tokend.sdk.api.integrations.dns.model.ClientResource
import org.tokend.sdk.api.integrations.dns.model.UserInfoResource
import org.tokend.sdk.api.integrations.dns.params.ClientsPageParams
import org.tokend.sdk.api.v3.base.PageQueryParams

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
                                 params: PageQueryParams? = null): ApiRequest<DataPage<BusinessResource>> {
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
                        JSONAPIDocument(emails.map { ClientResource(it) })
                )
        )
    }

    open fun getBusiness(businessId: String): ApiRequest<BusinessResource> {
        return MappedRetrofitApiRequest(
                dnsService.getBusiness(businessId),
                JSONAPIDocument<BusinessResource>::get
        )
    }

    open fun addClientBusiness(clientId: String,
                               businessId: String): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                dnsService.addClientBusiness(
                        clientId,
                        JSONAPIDocument(BusinessResource(businessId))
                )
        )
    }

    open fun getBusinesses(params: PageQueryParams? = null): ApiRequest<DataPage<BusinessResource>> {
        return MappedRetrofitApiRequest(
                dnsService.getBusinesses(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    open fun getUser(accountId: String): ApiRequest<UserInfoResource> {
        return MappedRetrofitApiRequest(
                dnsService.getUser(accountId),
                JSONAPIDocument<UserInfoResource>::get
        )
    }

    open fun getManyUsers(accounts: Collection<String>): ApiRequest<List<UserInfoResource>> {
        return MappedRetrofitApiRequest(
                dnsService.getManyUsers(DataEntity(mapOf(
                        "relationships" to mapOf(
                                "users" to DataEntity(accounts.map { mapOf("id" to it) })
                        )
                ))),
                JSONAPIDocument<List<UserInfoResource>>::get
        )
    }
}