package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.params.PagingOrder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.blobs.model.BlobType
import org.tokend.sdk.api.blobs.params.BlobPageParams
import org.tokend.sdk.api.identity.params.IdentitiesPageParams
import org.tokend.sdk.api.requests.model.base.RequestState
import org.tokend.sdk.api.sales.model.SaleState
import org.tokend.sdk.api.v3.assetpairs.params.AssetPairParams
import org.tokend.sdk.api.v3.assetpairs.params.AssetPairsPageParams
import org.tokend.sdk.api.v3.assets.params.AssetParams
import org.tokend.sdk.api.v3.assets.params.AssetsPageParams
import org.tokend.sdk.api.v3.balances.params.BalanceParams
import org.tokend.sdk.api.v3.balances.params.BalancesPageParams
import org.tokend.sdk.api.v3.fees.params.FeesPageParamsV3
import org.tokend.sdk.api.v3.history.params.ParticipantEffectsPageParams
import org.tokend.sdk.api.v3.history.params.ParticipantEffectsParams
import org.tokend.sdk.api.v3.offers.params.OfferParamsV3
import org.tokend.sdk.api.v3.offers.params.OffersPageParamsV3
import org.tokend.sdk.api.v3.orderbook.params.OrderBookPageParams
import org.tokend.sdk.api.v3.orderbook.params.OrderBookParamsV3
import org.tokend.sdk.api.v3.requests.params.RequestParamsV3
import org.tokend.sdk.api.v3.requests.params.RequestsPageParamsV3
import org.tokend.sdk.api.v3.sales.params.SaleParamsV3
import org.tokend.sdk.api.v3.sales.params.SalesPageParamsV3
import org.tokend.sdk.api.v3.transactions.params.TransactionsPageParams
import org.tokend.sdk.test.Config
import org.tokend.wallet.xdr.*
import java.math.BigDecimal
import java.util.*

class QueryParamsTest {
    private val accountId = Config.ADMIN_ACCOUNT.accountId

    @Test
    fun assetPairsParams() {
        val expected = "{filter[base_asset]=BTC, filter[policy]=4, filter[quote_asset]=ETH, include=base_asset, order=desc, page[order]=desc}"

        val params = AssetPairsPageParams(
                policies = listOf(AssetPairPolicy.CURRENT_PRICE_RESTRICTION),
                baseAsset = "BTC",
                quoteAsset = "ETH",
                include = listOf(AssetPairParams.Includes.BASE_ASSET),
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val buildParams = AssetPairsPageParams.Builder()
                .withPolicies(AssetPairPolicy.CURRENT_PRICE_RESTRICTION)
                .withBaseAsset("BTC")
                .withQuoteAsset("ETH")
                .withInclude(AssetPairParams.Includes.BASE_ASSET)
                .withPagingParams(PagingParamsV2(PagingOrder.DESC))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, buildParams.map().toSortedMap().toString())
    }

    @Test
    fun assetsParams() {
        val expected = "{filter[policy]=2, include=owner, order=desc, page[order]=desc}"

        val params = AssetsPageParams(
                policies = listOf(AssetPolicy.BASE_ASSET),
                include = listOf(AssetParams.Includes.OWNER),
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val builtParams = AssetsPageParams.Builder()
                .withPolicies(AssetPolicy.BASE_ASSET)
                .withInclude(AssetParams.Includes.OWNER)
                .withPagingParams(PagingParamsV2(PagingOrder.DESC))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun balancesParams() {
        val expected = "{filter[account]=$accountId, filter[asset]=ETH, include=account, order=desc, page[order]=desc}"

        val params = BalancesPageParams(
                asset = "ETH",
                account = accountId,
                include = listOf(BalanceParams.Includes.ACCOUNT),
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val builtParams = BalancesPageParams.Builder()
                .withAsset("ETH")
                .withAccount(accountId)
                .withInclude(BalanceParams.Includes.ACCOUNT)
                .withPagingParams(PagingParamsV2(PagingOrder.DESC))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun feesParams() {
        val expected = "{filter[account]=$accountId, filter[account_role]=1, filter[asset]=ETH, filter[fee_type]=0, filter[lower_bound]=1, filter[subtype]=1, filter[upper_bound]=10, include=account, order=desc, page[order]=desc}"

        val params = FeesPageParamsV3(
                asset = "ETH",
                type = FeeType.PAYMENT_FEE,
                subtype = 1,
                account = accountId,
                accountRole = 1,
                lowerBound = BigDecimal.ONE,
                upperBound = BigDecimal.TEN,
                include = listOf(FeesPageParamsV3.Includes.ACCOUNT),
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val builtParams = FeesPageParamsV3.Builder()
                .withAsset("ETH")
                .withType(FeeType.PAYMENT_FEE)
                .withSubtype(1)
                .withAccount(accountId)
                .withAccountRole(1)
                .withLowerBound(BigDecimal.ONE)
                .withUpperBound(BigDecimal.TEN)
                .withInclude(FeesPageParamsV3.Includes.ACCOUNT)
                .withPagingParams(PagingParamsV2(PagingOrder.DESC))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun offersParams() {
        val expected = "{filter[base_asset]=ETH, filter[base_balance]=10, filter[is_buy]=false, filter[order_book]=9223372036854775807, filter[owner]=$accountId, filter[quote_asset]=BTC, filter[quote_balance]=15, include=base_asset, order=desc, page[order]=desc}"

        val params = OffersPageParamsV3(
                baseBalance = "10",
                quoteBalance = "15",
                baseAsset = "ETH",
                quoteAsset = "BTC",
                ownerAccount = accountId,
                orderBook = Long.MAX_VALUE,
                isBuy = false,
                include = listOf(OfferParamsV3.Includes.BASE_ASSET),
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val builtParams = OffersPageParamsV3.Builder()
                .withBaseBalance("10")
                .withQuoteBalance("15")
                .withBaseAsset("ETH")
                .withQuoteAsset("BTC")
                .withOwnerAccount(accountId)
                .withOrderBook(Long.MAX_VALUE)
                .withIsBuy(false)
                .withInclude(OfferParamsV3.Includes.BASE_ASSET)
                .withPagingParams(PagingParamsV2(PagingOrder.DESC))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun requestsParams() {
        val expected = "{filter[missing_pending_tasks]=1, filter[pending_tasks]=2, filter[pending_tasks_any_of]=3, filter[pending_tasks_not_set]=4, filter[requestor]=$accountId, filter[reviewer]=$accountId, filter[state]=3, filter[type]=10, include=request_details, order=desc, page[order]=desc}"

        val params = RequestsPageParamsV3(
                reviewer = accountId,
                requestor = accountId,
                state = RequestState.APPROVED,
                type = ReviewableRequestType.CREATE_ASSET,
                missingPendingTasks = 1,
                pendingTasks = 2,
                pendingTasksAnyOf = 3,
                pendingTasksNotSet = 4,
                includes = listOf(RequestParamsV3.Includes.REQUEST_DETAILS),
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val builtParams = RequestsPageParamsV3.Builder()
                .withReviewer(accountId)
                .withRequestor(accountId)
                .withState(RequestState.APPROVED)
                .withType(ReviewableRequestType.CREATE_ASSET)
                .withMissingPendingTasks(1)
                .withPendingTasks(2)
                .withPendingTasksAnyOf(3)
                .withPendingTasksNotSet(4)
                .withInclude(RequestParamsV3.Includes.REQUEST_DETAILS)
                .withPagingParams(PagingParamsV2(PagingOrder.DESC))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun salesParams() {
        val expected = "{filter[base_asset]=BTC, filter[max_end_time]=2019-02-08T09:38:40Z, filter[max_hard_cap]=1, filter[max_start_time]=2019-02-08T09:28:40Z, filter[min_soft_cap]=10, filter[owner]=$accountId, filter[sale_type]=2, filter[state]=1, include=base_asset, order=desc, page[order]=desc}"

        val params = SalesPageParamsV3(
                baseAsset = "BTC",
                owner = accountId,
                state = SaleState.OPEN,
                maxEndTime = Date(1549618720 * 1000L),
                maxStartTime = Date(1549618120 * 1000L),
                maxHardCap = BigDecimal.ONE,
                minSoftCap = BigDecimal.TEN,
                saleType = SaleType.CROWD_FUNDING,
                includes = listOf(SaleParamsV3.Includes.BASE_ASSET),
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val builtParams = SalesPageParamsV3.Builder()
                .withBaseAsset("BTC")
                .withOwner(accountId)
                .withState(SaleState.OPEN)
                .withMaxEndTime(Date(1549618720 * 1000L))
                .withMaxStartTime(Date(1549618120 * 1000L))
                .withMaxHardCap(BigDecimal.ONE)
                .withMinSoftCap(BigDecimal.TEN)
                .withSaleType(SaleType.CROWD_FUNDING)
                .withInclude(SaleParamsV3.Includes.BASE_ASSET)
                .withPagingParams(PagingParamsV2(PagingOrder.DESC))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun transactionsParams() {
        val expected = "{filter[account_id]=$accountId, order=desc, page[order]=desc}"

        val params = TransactionsPageParams(
                account = accountId,
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val builtParams = TransactionsPageParams.Builder()
                .withAccount(accountId)
                .withPagingParams(PagingParamsV2(PagingOrder.DESC))
                .build()


        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun participantEffectsParams() {
        val expected = "{cursor=6, filter[account]=$accountId, filter[balance]=superbalance, include=operation,operation.details,effect, limit=18, order=desc, page=6, page[cursor]=6, page[limit]=18, page[number]=6, page[order]=desc}"

        val params = ParticipantEffectsPageParams(
                account = accountId,
                balance = "superbalance",
                include = listOf(
                        ParticipantEffectsParams.Includes.OPERATION,
                        ParticipantEffectsParams.Includes.OPERATION_DETAILS,
                        ParticipantEffectsParams.Includes.EFFECT
                ),
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 18,
                        page = "6"
                )

        )

        val builtParams = ParticipantEffectsPageParams.Builder()
                .withAccount(accountId)
                .withBalance("superbalance")
                .withInclude(
                        ParticipantEffectsParams.Includes.OPERATION,
                        ParticipantEffectsParams.Includes.OPERATION_DETAILS,
                        ParticipantEffectsParams.Includes.EFFECT
                )
                .withPagingParams(
                        PagingParamsV2(
                                order = PagingOrder.DESC,
                                limit = 18,
                                page = "6"
                        )
                )
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun orderBookPageParams() {
        val expected = "{cursor=8, filter[base_asset]=BTC, filter[is_buy]=false, filter[quote_asset]=ETH, include=base_asset,quote_asset, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = OrderBookPageParams(
                baseAsset = "BTC",
                quoteAsset = "ETH",
                isBuy = false,
                include = listOf(
                        OrderBookPageParams.Includes.BASE_ASSET,
                        OrderBookPageParams.Includes.QUOTE_ASSET
                ),
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                )
        )

        val builtParams = OrderBookPageParams.Builder()
                .withBaseAsset("BTC")
                .withQuoteAsset("ETH")
                .withIsBuy(false)
                .withInclude(
                        OrderBookPageParams.Includes.BASE_ASSET,
                        OrderBookPageParams.Includes.QUOTE_ASSET
                )
                .withPagingParams(
                        PagingParamsV2(
                                order = PagingOrder.DESC,
                                limit = 10,
                                page = "8"
                        )
                )
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun identityParams() {
        val expected = "{cursor=8, filter[address]=$accountId, filter[email]=email, include=, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = IdentitiesPageParams(
                address = accountId,
                email = "email",
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                ),
                include = emptyList()
        )

        val builtParams = IdentitiesPageParams.Builder()
                .withAddress(accountId)
                .withEmail("email")
                .withPagingParams(
                        PagingParamsV2(
                                order = PagingOrder.DESC,
                                limit = 10,
                                page = "8"
                        )
                )
                .withInclude()
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun blobsParams() {
        val expected = "{cursor=8, filter[deleted]=false, filter[type]=128, include=, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = BlobPageParams(
                type = BlobType.CHARLIE,
                isDeleted = false,
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                ),
                include = emptyList()
        )

        val builtParams = BlobPageParams.Builder()
                .withType(BlobType.CHARLIE)
                .withIsDeleted(false)
                .withPagingParams(
                        PagingParamsV2(
                                order = PagingOrder.DESC,
                                limit = 10,
                                page = "8"
                        )
                )
                .withInclude()
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun orderBookParams() {
        val expected = "{include=buy_entries,sell_entries, max_entries=50}"

        val params = OrderBookParamsV3(
                maxEntries = 50,
                include = listOf(
                        OrderBookParamsV3.Includes.BUY_ENTRIES,
                        OrderBookParamsV3.Includes.SELL_ENTRIES
                )
        )

        val builtParams = OrderBookParamsV3.Builder()
                .withMaxEntries(50)
                .withInclude(
                        OrderBookParamsV3.Includes.BUY_ENTRIES,
                        OrderBookParamsV3.Includes.SELL_ENTRIES
                )
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }
}