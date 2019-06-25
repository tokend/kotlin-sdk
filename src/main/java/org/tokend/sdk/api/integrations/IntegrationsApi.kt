package org.tokend.sdk.api.integrations

import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.integrations.dns.DnsApi
import org.tokend.sdk.api.integrations.dns.DnsService
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider

open class IntegrationsApi(
        rootUrl: String,
        requestSigner: RequestSigner?,
        tfaCallback: TfaCallback?,
        cookieJarProvider: CookieJarProvider?,
        userAgent: String?,
        withLogs: Boolean
) : BaseApi(
        rootUrl, requestSigner, tfaCallback, cookieJarProvider,
        userAgent, withLogs
) {
    open val dns: DnsApi by lazy {
        DnsApi(getService(DnsService::class.java))
    }
}