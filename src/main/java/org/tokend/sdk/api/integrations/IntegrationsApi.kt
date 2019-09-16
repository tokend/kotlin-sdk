package org.tokend.sdk.api.integrations

import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.integrations.dns.DnsApi
import org.tokend.sdk.api.integrations.dns.DnsService
import org.tokend.sdk.api.integrations.fiat.FiatApi
import org.tokend.sdk.api.integrations.fiat.FiatService
import org.tokend.sdk.api.integrations.locator.LocatorApi
import org.tokend.sdk.api.integrations.locator.LocatorService
import org.tokend.sdk.api.integrations.marketplace.MarketplaceApi
import org.tokend.sdk.api.integrations.marketplace.MarketplaceService
import org.tokend.sdk.api.integrations.paymentproxy.PaymentProxyApi
import org.tokend.sdk.api.integrations.paymentproxy.PaymentProxyService
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

    open val fiat: FiatApi by lazy {
        FiatApi(getService(FiatService::class.java))
    }

    open val paymentProxy: PaymentProxyApi by lazy {
        PaymentProxyApi(getService(PaymentProxyService::class.java))
    }

    open val marketplace: MarketplaceApi by lazy {
        MarketplaceApi(getService(MarketplaceService::class.java))
    }

    open val locator: LocatorApi by lazy {
        LocatorApi(getService(LocatorService::class.java))
    }
}