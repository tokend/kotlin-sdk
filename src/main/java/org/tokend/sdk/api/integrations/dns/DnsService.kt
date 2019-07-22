package org.tokend.sdk.api.integrations.dns

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.integrations.dns.model.BusinessResource
import org.tokend.sdk.api.integrations.dns.model.ClientResource
import retrofit2.Call
import retrofit2.http.*

interface DnsService {
    @GET("integrations/dns/businesses/{id}/clients")
    @JvmSuppressWildcards
    fun getBusinessClients(@Path("id") id: String,
                           @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<ClientResource>>>

    @GET("integrations/dns/clients/{id}/businesses")
    @JvmSuppressWildcards
    fun getClientBusinesses(@Path("id") id: String,
                            @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<BusinessResource>>>

    @POST("integrations/dns/clients/{id}/businesses")
    @JvmSuppressWildcards
    fun addClientBusiness(@Path("id") id: String,
                          @Body body: JSONAPIDocument<BusinessResource>): Call<Void>

    @POST("integrations/dns/businesses/{id}/clients")
    @JvmSuppressWildcards
    fun inviteClients(@Path("id") id: String,
                      @Body body: JSONAPIDocument<List<ClientResource>>): Call<Void>

    @GET("integrations/dns/businesses/{id}")
    @JvmSuppressWildcards
    fun getBusiness(@Path("id") id: String): Call<JSONAPIDocument<BusinessResource>>
}