package org.tokend.sdk.api.integrations

import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.custom.CustomRequestsService
import org.tokend.sdk.api.integrations.booking.BookingApi
import org.tokend.sdk.api.integrations.booking.BookingService
import org.tokend.sdk.api.integrations.cards.CardsApi
import org.tokend.sdk.api.integrations.dns.DnsApi
import org.tokend.sdk.api.integrations.dns.DnsService
import org.tokend.sdk.api.integrations.escrow.EscrowApi
import org.tokend.sdk.api.integrations.escrow.EscrowService
import org.tokend.sdk.api.integrations.fiat.FiatApi
import org.tokend.sdk.api.integrations.fiat.FiatService
import org.tokend.sdk.api.integrations.invoices.InvoicesApi
import org.tokend.sdk.api.integrations.locator.LocatorApi
import org.tokend.sdk.api.integrations.locator.LocatorService
import org.tokend.sdk.api.integrations.marketplace.MarketplaceApi
import org.tokend.sdk.api.integrations.marketplace.MarketplaceService
import org.tokend.sdk.api.integrations.paymentproxy.PaymentProxyApi
import org.tokend.sdk.api.integrations.paymentproxy.PaymentProxyService
import org.tokend.sdk.api.integrations.recpayments.RecurringPaymentsApi
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider
import java.util.concurrent.Executor

open class IntegrationsApi(
        rootUrl: String,
        requestSigner: RequestSigner?,
        tfaCallback: TfaCallback?,
        cookieJarProvider: CookieJarProvider?,
        userAgent: String?,
        withLogs: Boolean,
        asyncCallbackExecutor: Executor
) : BaseApi(
        rootUrl, requestSigner, tfaCallback, cookieJarProvider,
        userAgent, asyncCallbackExecutor, withLogs
) {
    protected open val customRequests: CustomRequestsApi by lazy {
        CustomRequestsApi(getService(CustomRequestsService::class.java))
    }

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

    open val booking: BookingApi by lazy {
        BookingApi(getService(BookingService::class.java))
    }

    open val escrow: EscrowApi by lazy {
        EscrowApi(getService(EscrowService::class.java))
    }

    open val recurringPayments: RecurringPaymentsApi by lazy {
        RecurringPaymentsApi(customRequests)
    }

    open val invoices: InvoicesApi by lazy {
        InvoicesApi(customRequests)
    }

    open val cards: CardsApi by lazy {
        CardsApi(customRequests)
    }
}