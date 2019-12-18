package org.tokend.sdk.api.v3

import org.tokend.sdk.api.base.BaseApi
import org.tokend.sdk.api.v3.assetpairs.AssetPairsApi
import org.tokend.sdk.api.v3.assetpairs.AssetPairsService
import org.tokend.sdk.api.v3.atomicswap.AtomicSwapsApi
import org.tokend.sdk.api.v3.atomicswap.AtomicSwapsService
import org.tokend.sdk.api.v3.fees.FeesApiV3
import org.tokend.sdk.api.v3.fees.FeesServiceV3
import org.tokend.sdk.api.v3.offers.OffersApiV3
import org.tokend.sdk.api.v3.offers.OffersServiceV3
import org.tokend.sdk.api.v3.orderbook.OrderBooksApi
import org.tokend.sdk.api.v3.orderbook.OrderBooksService
import org.tokend.sdk.api.v3.polls.PollsApi
import org.tokend.sdk.api.v3.polls.PollsService
import org.tokend.sdk.api.v3.sales.SalesApiV3
import org.tokend.sdk.api.v3.sales.SalesServiceV3
import org.tokend.sdk.api.v3.swaps.SwapsApi
import org.tokend.sdk.api.v3.swaps.SwapsService
import org.tokend.sdk.signing.RequestSigner
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.utils.CookieJarProvider

open class TokenDApiV3(rootUrl: String,
                       requestSigner: RequestSigner?,
                       tfaCallback: TfaCallback?,
                       cookieJarProvider: CookieJarProvider?,
                       userAgent: String?,
                       withLogs: Boolean
) : BaseApi(
        rootUrl, requestSigner, tfaCallback, cookieJarProvider,
        userAgent, withLogs
) {
    open val assetPairs: AssetPairsApi by lazy {
        AssetPairsApi(getService(AssetPairsService::class.java))
    }

    open val fees: FeesApiV3 by lazy {
        FeesApiV3(getService(FeesServiceV3::class.java))
    }

    open val offers: OffersApiV3 by lazy {
        OffersApiV3(getService(OffersServiceV3::class.java))
    }

    open val sales: SalesApiV3 by lazy {
        SalesApiV3(getService(SalesServiceV3::class.java))
    }

    open val orderBooks: OrderBooksApi by lazy {
        OrderBooksApi(getService(OrderBooksService::class.java))
    }

    open val polls: PollsApi by lazy {
        PollsApi(getService(PollsService::class.java))
    }

    open val atomicSwaps: AtomicSwapsApi by lazy {
        AtomicSwapsApi(getService(AtomicSwapsService::class.java))
    }

    open val swaps: SwapsApi by lazy {
        SwapsApi(getService(SwapsService::class.java))
    }
}