package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.params.PagingOrder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.base.params.PagingParamsV3
import org.tokend.sdk.api.identity.params.IdentitiesPageParams
import org.tokend.sdk.api.integrations.marketplace.params.MarketplaceOfferParams
import org.tokend.sdk.api.integrations.marketplace.params.MarketplaceOffersPageParams
import org.tokend.sdk.api.v3.assetpairs.params.AssetPairParams
import org.tokend.sdk.api.v3.assetpairs.params.AssetPairsPageParams
import org.tokend.sdk.api.v3.assets.model.AssetState
import org.tokend.sdk.api.v3.assets.params.AssetParams
import org.tokend.sdk.api.v3.assets.params.AssetsPageParams
import org.tokend.sdk.api.v3.atomicswap.params.AtomicSwapAskParams
import org.tokend.sdk.api.v3.atomicswap.params.AtomicSwapAsksPageParams
import org.tokend.sdk.api.v3.balances.params.BalanceParams
import org.tokend.sdk.api.v3.balances.params.BalancesPageParams
import org.tokend.sdk.api.v3.base.JsonApiQueryMapBuilder
import org.tokend.sdk.api.v3.fees.params.FeesPageParamsV3
import org.tokend.sdk.api.v3.history.params.OperationParams
import org.tokend.sdk.api.v3.history.params.OperationsPageParams
import org.tokend.sdk.api.v3.history.params.ParticipantEffectsPageParams
import org.tokend.sdk.api.v3.history.params.ParticipantEffectsParams
import org.tokend.sdk.api.v3.offers.params.OfferParamsV3
import org.tokend.sdk.api.v3.offers.params.OffersPageParamsV3
import org.tokend.sdk.api.v3.orderbook.params.MatchesPageParams
import org.tokend.sdk.api.v3.orderbook.params.OrderBookPageParams
import org.tokend.sdk.api.v3.orderbook.params.OrderBookParamsV3
import org.tokend.sdk.api.v3.polls.model.PollState
import org.tokend.sdk.api.v3.polls.params.PollParams
import org.tokend.sdk.api.v3.polls.params.PollsPageParams
import org.tokend.sdk.api.v3.requests.model.RequestState
import org.tokend.sdk.api.v3.requests.params.RequestParamsV3
import org.tokend.sdk.api.v3.requests.params.RequestsPageParamsV3
import org.tokend.sdk.api.v3.sales.model.SaleState
import org.tokend.sdk.api.v3.sales.params.SaleParamsV3
import org.tokend.sdk.api.v3.sales.params.SalesPageParamsV3
import org.tokend.sdk.api.v3.swaps.model.SwapState
import org.tokend.sdk.api.v3.swaps.params.SwapParams
import org.tokend.sdk.api.v3.swaps.params.SwapsPageParams
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
        val expected = "{filter[owner]=$accountId, filter[policy]=2, filter[state]=0, include=owner, order=desc, page[order]=desc}"

        val params = AssetsPageParams(
                owner = accountId,
                policies = listOf(AssetPolicy.BASE_ASSET),
                state = AssetState.ACTIVE,
                include = listOf(AssetParams.Includes.OWNER),
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val builtParams = AssetsPageParams.Builder()
                .withOwner(accountId)
                .withPolicies(AssetPolicy.BASE_ASSET)
                .withState(AssetState.ACTIVE)
                .withInclude(AssetParams.Includes.OWNER)
                .withPagingParams(PagingParamsV2(PagingOrder.DESC))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun balancesParams() {
        val expected = "{filter[asset]=ETH, filter[asset_owner]=OWNER, filter[owner]=$accountId, include=account,state, order=desc, page[order]=desc}"

        val params = BalancesPageParams(
                asset = "ETH",
                account = accountId,
                assetOwner = "OWNER",
                include = listOf(BalanceParams.Includes.ACCOUNT, BalanceParams.Includes.STATE),
                pagingParams = PagingParamsV2(PagingOrder.DESC)
        )

        val builtParams = BalancesPageParams.Builder()
                .withAsset("ETH")
                .withAccount(accountId)
                .withAssetOwner("OWNER")
                .withInclude(BalanceParams.Includes.ACCOUNT, BalanceParams.Includes.STATE)
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
        val expected = "{filter[base_asset]=BTC, filter[max_end_time]=2019-02-08T09:38:40.000Z, filter[max_hard_cap]=1, filter[max_start_time]=2019-02-08T09:28:40.000Z, filter[min_soft_cap]=10, filter[owner]=$accountId, filter[sale_type]=2, filter[state]=1, include=base_asset, order=desc, page[order]=desc}"

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
        val expected = "{cursor=6, filter[account]=$accountId, filter[asset]=OLE, filter[balance]=superbalance, include=operation,operation.details,effect, limit=18, order=desc, page=6, page[cursor]=6, page[limit]=18, page[number]=6, page[order]=desc}"

        val params = ParticipantEffectsPageParams(
                account = accountId,
                balance = "superbalance",
                asset = "OLE",
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
                .withAsset("OLE")
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
        val expected = "{cursor=8, filter[address]=$accountId, filter[email]=email, filter[identifier]=id, filter[passport]=qwerty, filter[phone]=+380983332211, include=, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = IdentitiesPageParams(
                address = accountId,
                email = "email",
                identifier = "id",
                phone = "+380983332211",
                passport = "qwerty",
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
                .withIdentifier("id")
                .withPhone("+380983332211")
                .withPassport("qwerty")
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

    @Test
    fun pollsPageParams() {
        val expected = "{cursor=8, filter[max_end_time]=2028-08-11T14:58:40.000Z, filter[max_start_time]=2025-06-11T05:12:00.000Z, filter[min_end_time]=2022-04-10T19:25:20.000Z, filter[min_start_time]=2019-02-08T09:38:40.000Z, filter[owner]=GBA4EX43M25UPV4WIE6RRMQOFTWXZZRIPFAI5VPY6Z2ZVVXVWZ6NEOOB, filter[permission_type]=0, filter[poll_type]=0, filter[state]=3, filter[vote_confirmation]=true, include=participation,participation.votes, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = PollsPageParams(
                owner = accountId,
                permissionType = 0,
                pollType = PollType.SINGLE_CHOICE,
                voteConfirmation = true,
                minStartTime = Date(1549618720 * 1000L),
                minEndTime = Date(1649618720 * 1000L),
                maxStartTime = Date(1749618720 * 1000L),
                maxEndTime = Date(1849618720 * 1000L),
                state = PollState.FAILED,
                include = listOf(
                        PollParams.Includes.PARTICIPATION,
                        PollParams.Includes.PARTICIPATION_VOTES
                ),
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                )
        )

        val builtParams = PollsPageParams.Builder()
                .withOwner(accountId)
                .withPermissionType(0)
                .withPollType(PollType.SINGLE_CHOICE)
                .withVoteConfirmation(true)
                .withMinStartTime(Date(1549618720 * 1000L))
                .withMinEndTime(Date(1649618720 * 1000L))
                .withMaxStartTime(Date(1749618720 * 1000L))
                .withMaxEndTime(Date(1849618720 * 1000L))
                .withState(PollState.FAILED)
                .withInclude(
                        PollParams.Includes.PARTICIPATION,
                        PollParams.Includes.PARTICIPATION_VOTES
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
    fun atomicSwapAsksPageParams() {
        val expected = "{cursor=8, filter[base_asset]=MAA, filter[owner]=$accountId, filter[quote_assets]=BTC,ETH,DOGE, include=base_asset,base_balance,owner,quote_assets, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = AtomicSwapAsksPageParams(
                baseAsset = "MAA",
                owner = accountId,
                quoteAssets = listOf("BTC", "ETH", "DOGE"),
                include = listOf(
                        AtomicSwapAskParams.Includes.BASE_ASSET,
                        AtomicSwapAskParams.Includes.BASE_BALANCE,
                        AtomicSwapAskParams.Includes.OWNER,
                        AtomicSwapAskParams.Includes.QUOTE_ASSETS
                ),
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                )
        )

        val builtParams = AtomicSwapAsksPageParams.Builder()
                .withBaseAsset("MAA")
                .withOwner(accountId)
                .withQuoteAssets("BTC", "ETH", "DOGE")
                .withInclude(
                        AtomicSwapAskParams.Includes.BASE_ASSET,
                        AtomicSwapAskParams.Includes.BASE_BALANCE,
                        AtomicSwapAskParams.Includes.OWNER,
                        AtomicSwapAskParams.Includes.QUOTE_ASSETS
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
    fun marketplaceOffersPageParams() {
        val expected = "{cursor=8, filter[owner]=$accountId, include=payment_methods, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = MarketplaceOffersPageParams(
                owner = accountId,
                include = listOf(MarketplaceOfferParams.Includes.PAYMENT_METHODS),
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                )
        )

        val builtParams = MarketplaceOffersPageParams.Builder()
                .withOwner(accountId)
                .withInclude(MarketplaceOfferParams.Includes.PAYMENT_METHODS)
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
    fun swapsPageParams() {
        val expected = "{cursor=8, filter[destination]=DEST_$accountId, filter[destination_balance]=DEST_BALANCE, filter[source]=$accountId, filter[source_balance]=SOURCE_BALANCE, filter[state]=1, include=asset,destination_balance,source_balance, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = SwapsPageParams(
                source = accountId,
                destination = "DEST_" + accountId,
                sourceBalance = "SOURCE_BALANCE",
                destinationBalance = "DEST_BALANCE",
                state = SwapState.OPEN,
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                ),
                include = listOf(
                        SwapParams.Includes.ASSET, SwapParams.Includes.DESTINATION_BALANCE,
                        SwapParams.Includes.SOURCE_BALANCE

                )
        )

        val builtParams = SwapsPageParams.Builder()
                .withSource(accountId)
                .withDestination("DEST_" + accountId)
                .withSourceBalance("SOURCE_BALANCE")
                .withDestinationBalance("DEST_BALANCE")
                .withState(SwapState.OPEN)
                .withPagingParams(PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                ))
                .withInclude(SwapParams.Includes.ASSET, SwapParams.Includes.DESTINATION_BALANCE,
                        SwapParams.Includes.SOURCE_BALANCE)
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun operationsPageParams() {
        val expected = "{cursor=8, filter[types]=20,11, include=operation.details, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = OperationsPageParams(
                types = listOf(OperationType.CHECK_SALE_STATE, OperationType.MANAGE_ASSET),
                include = listOf(OperationParams.Includes.OPERATION_DETAILS),
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                )
        )

        val builtParams = OperationsPageParams.Builder()
                .withTypes(OperationType.CHECK_SALE_STATE, OperationType.MANAGE_ASSET)
                .withInclude(OperationParams.Includes.OPERATION_DETAILS)
                .withPagingParams(PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                ))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }

    @Test
    fun jsonApiQueryMapBuilder() {
        val expected = "{extra=param, filter[owner]=$accountId, include=a,b,c,d, my=param, page[cursor]=cursor432, page[limit]=42, page[order]=desc}"

        val map = JsonApiQueryMapBuilder()
                .filter("owner", accountId)
                .filter("optional", null)
                .include("a", "b", "c", "d")
                .param("my", "param")
                .append(PagingParamsV3.withCursor(
                        cursor = "cursor432",
                        limit = 42,
                        order = PagingOrder.DESC
                ))
                .append(mapOf(
                        "extra" to "param"
                ))
                .build()
                .toSortedMap()

        Assert.assertEquals(expected, map.toString())
        Assert.assertFalse(map.toString().contains("optional"))
    }

    @Test
    fun matchesParams() {
        val expected = "{cursor=8, filter[base_asset]=BTC, filter[order_book]=3, filter[quote_asset]=USD, include=base_asset, limit=10, order=desc, page=8, page[cursor]=8, page[limit]=10, page[number]=8, page[order]=desc}"

        val params = MatchesPageParams(
                baseAsset = "BTC",
                quoteAsset = "USD",
                orderBookId = "3",
                include = listOf("base_asset"),
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                )
        )

        val builtParams = MatchesPageParams.Builder()
                .withBaseAsset("BTC")
                .withQuoteAsset("USD")
                .withOrderBookId("3")
                .withInclude("base_asset")
                .withPagingParams(PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 10,
                        page = "8"
                ))
                .build()

        Assert.assertEquals(expected, params.map().toSortedMap().toString())
        Assert.assertEquals(expected, builtParams.map().toSortedMap().toString())
    }
}