package org.tokend.sdk.api.integrations

import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.custom.CustomRequestsService
import org.tokend.sdk.api.integrations.booking.BookingApi
import org.tokend.sdk.api.integrations.booking.BookingService
import org.tokend.sdk.api.integrations.kycprovider.KycProviderApi
import org.tokend.sdk.api.integrations.marketplace.MarketplaceApi
import org.tokend.sdk.api.integrations.marketplace.MarketplaceService
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider
import java.util.concurrent.Executor

open class IntegrationsApi(
    rootUrl: String,
    requestSigner: RequestSigner?,
    tfaCallback: TfaCallback?,
    cookieJarProvider: CookieJarProvider?,
    extraHeaders: Map<String, String?>?,
    withLogs: Boolean,
    asyncCallbackExecutor: Executor
) : BaseApi(
    rootUrl, requestSigner, tfaCallback, cookieJarProvider,
    extraHeaders, asyncCallbackExecutor, withLogs
) {
    protected open val customRequests: CustomRequestsApi by lazy {
        CustomRequestsApi(getService(CustomRequestsService::class.java))
    }

    open val marketplace: MarketplaceApi by lazy {
        MarketplaceApi(getService(MarketplaceService::class.java))
    }

    open val booking: BookingApi by lazy {
        BookingApi(getService(BookingService::class.java))
    }

    open val kycProvider: KycProviderApi by lazy {
        KycProviderApi(customRequests)
    }
}