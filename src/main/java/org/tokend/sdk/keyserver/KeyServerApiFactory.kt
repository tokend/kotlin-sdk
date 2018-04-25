package org.tokend.sdk.keyserver

import org.tokend.sdk.api.ApiFactory
import org.tokend.sdk.api.requests.CookieJarProvider
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object KeyServerApiFactory {
    private var keyServerApi: KeyServerApi? = null
    private var keyServerApiUrl: String? = null
    private var cookieJarProvider: CookieJarProvider? = null


    fun getApiService(keyServerApiUrl: String, cookieJar: CookieJarProvider? = null): KeyServerApi {
        if (keyServerApi == null || this.keyServerApiUrl != keyServerApiUrl || cookieJarProvider != cookieJar) {
            this.keyServerApiUrl = keyServerApiUrl
            this.cookieJarProvider = cookieJar
            val retrofit = createBaseRetrofitConfig(keyServerApiUrl).build()
            keyServerApi = retrofit.create(KeyServerApi::class.java)
        }
        return keyServerApi!!
    }

    private fun createBaseRetrofitConfig(apiUrl: String, cookieJar: CookieJarProvider? = null): Retrofit.Builder {
        val client = ApiFactory.getBaseHttpClient(cookieJar = cookieJar)
        return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(ApiFactory.createBaseJsonConverterFactory())
                .baseUrl(apiUrl)
                .client(client)
    }
}