package org.tokend.sdk.api.integrations.dns

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.integrations.dns.model.BusinessResource
import org.tokend.sdk.api.integrations.dns.model.ClientResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface DnsService {
    @GET("integrations/dns/businesses/{id}/clients")
    @JvmSuppressWildcards
    fun getBusinessClients(@Path("id") id: String,
                           @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<ClientResource>>>

    @GET("integrations/dns/clients/{id}/businesses")
    @JvmSuppressWildcards
    fun getClientBusinesses(@Path("id") id: String,
                            @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<BusinessResource>>>
}