package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.params.PagingOrder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.requests.model.base.RequestState
import org.tokend.sdk.api.sales.model.SaleState
import org.tokend.sdk.api.v3.accounts.params.AccountParamsV3
import org.tokend.sdk.api.v3.accounts.params.AccountsPageParamsV3
import org.tokend.sdk.api.v3.assetpairs.params.AssetPairParams
import org.tokend.sdk.api.v3.assetpairs.params.AssetPairsPageParams
import org.tokend.sdk.api.v3.assets.params.AssetParams
import org.tokend.sdk.api.v3.assets.params.AssetsPageParams
import org.tokend.sdk.api.v3.balances.params.BalanceParams
import org.tokend.sdk.api.v3.balances.params.BalancesPageParams
import org.tokend.sdk.api.v3.fees.params.FeeParamsV3
import org.tokend.sdk.api.v3.fees.params.FeesPageParamsV3
import org.tokend.sdk.api.v3.history.params.ParticipantEffectsPageParams
import org.tokend.sdk.api.v3.history.params.ParticipantEffectsParams
import org.tokend.sdk.api.v3.offers.params.OfferParamsV3
import org.tokend.sdk.api.v3.offers.params.OffersPageParamsV3
import org.tokend.sdk.api.v3.orderbook.params.OrderBookPageParams
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
    fun accountsParams() {
        val expected = "{include=balances.state, filter[account_type]=5, filter[signer_type]=2048, filter[is_blocked]=false}"

        val params = AccountsPageParamsV3(
                accountTypes = listOf(AccountType.NOT_VERIFIED),
                signerTypes = listOf(SignerType.ACCOUNT_MANAGER),
                isBlocked = false,
                include = listOf(AccountParamsV3.Includes.BALANCES_STATE)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun accountsParamsBuilder() {
        val expected = "{include=balances.state, filter[account_type]=5, filter[signer_type]=2048, filter[is_blocked]=false}"

        val params = AccountsPageParamsV3.Builder()
                .withAccountTypes(AccountType.NOT_VERIFIED)
                .withSignerTypes(SignerType.ACCOUNT_MANAGER)
                .withIsBlocked(false)
                .withInclude(AccountParamsV3.Includes.BALANCES_STATE)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun assetPairsParams() {
        val expected = "{include=base_asset, filter[policy]=4, filter[base_asset]=BTC, filter[quote_asset]=ETH}"

        val params = AssetPairsPageParams(
                policies = listOf(AssetPairPolicy.CURRENT_PRICE_RESTRICTION),
                baseAsset = "BTC",
                quoteAsset = "ETH",
                include = listOf(AssetPairParams.Includes.BASE_ASSET)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun assetPairsParamsBuilder() {
        val expected = "{include=base_asset, filter[policy]=4, filter[base_asset]=BTC, filter[quote_asset]=ETH}"

        val params = AssetPairsPageParams.Builder()
                .withPolicies(AssetPairPolicy.CURRENT_PRICE_RESTRICTION)
                .withBaseAsset("BTC")
                .withQuoteAsset("ETH")
                .withInclude(AssetPairParams.Includes.BASE_ASSET)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun assetsParams() {
        val expected = "{include=owner, filter[policy]=2}"

        val params = AssetsPageParams(
                policies = listOf(AssetPolicy.BASE_ASSET),
                include = listOf(AssetParams.Includes.OWNER)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun assetParamsBuilder() {
        val expected = "{include=owner, filter[policy]=2}"

        val params = AssetsPageParams.Builder()
                .withPolicies(AssetPolicy.BASE_ASSET)
                .withInclude(AssetParams.Includes.OWNER)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun balancesParams() {
        val expected = "{include=account, filter[asset]=ETH, filter[account]=$accountId}"

        val params = BalancesPageParams(
                asset = "ETH",
                account = accountId,
                include = listOf(BalanceParams.Includes.ACCOUNT)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun balanceParamsBuilder() {
        val expected = "{include=account, filter[asset]=ETH, filter[account]=$accountId}"

        val params = BalancesPageParams.Builder()
                .withAsset("ETH")
                .withAccount(accountId)
                .withInclude(BalanceParams.Includes.ACCOUNT)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun feesParams() {
        val expected = "{include=account, filter[asset]=ETH, filter[fee_type]=0, filter[subtype]=1, filter[account_id]=$accountId, filter[account_type]=10, filter[lower_bound]=1, filter[upper_bound]=10}"

        val params = FeesPageParamsV3(
                asset = "ETH",
                type = FeeType.PAYMENT_FEE,
                subtype = 1,
                account = accountId,
                accountType = AccountType.VERIFIED,
                lowerBound = BigDecimal.ONE,
                upperBound = BigDecimal.TEN,
                include = listOf(FeeParamsV3.Includes.ACCOUNT)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun feesParamsBuilder() {
        val expected = "{include=account, filter[asset]=ETH, filter[fee_type]=0, filter[subtype]=1, filter[account_id]=$accountId, filter[account_type]=10, filter[lower_bound]=1, filter[upper_bound]=10}"

        val params = FeesPageParamsV3.Builder()
                .withAsset("ETH")
                .withType(FeeType.PAYMENT_FEE)
                .withSubtype(1)
                .withAccount(accountId)
                .withAccountType(AccountType.VERIFIED)
                .withLowerBound(BigDecimal.ONE)
                .withUpperBound(BigDecimal.TEN)
                .withInclude(FeeParamsV3.Includes.ACCOUNT)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun offersParams() {
        val expected = "{include=base_asset, filter[base_balance]=10, filter[quote_balance]=15, filter[base_asset]=ETH, filter[quote_asset]=BTC, filter[owner]=$accountId, filter[order_book]=9223372036854775807, filter[is_buy]=false}"

        val params = OffersPageParamsV3(
                baseBalance = "10",
                quoteBalance = "15",
                baseAsset = "ETH",
                quoteAsset = "BTC",
                ownerAccount = accountId,
                orderBook = Long.MAX_VALUE,
                isBuy = false,
                include = listOf(OfferParamsV3.Includes.BASE_ASSET)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun offersParamsBuilder() {
        val expected = "{include=base_asset, filter[base_balance]=10, filter[quote_balance]=15, filter[base_asset]=ETH, filter[quote_asset]=BTC, filter[owner]=$accountId, filter[order_book]=9223372036854775807, filter[is_buy]=false}"

        val params = OffersPageParamsV3.Builder()
                .withBaseBalance("10")
                .withQuoteBalance("15")
                .withBaseAsset("ETH")
                .withQuoteAsset("BTC")
                .withOwnerAccount(accountId)
                .withOrderBook(Long.MAX_VALUE)
                .withIsBuy(false)
                .withInclude(OfferParamsV3.Includes.BASE_ASSET)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun requestsParams() {
        val updated = 1549618720L

        val expected = "{include=request_details, filter[reviewer]=$accountId, filter[requestor]=$accountId, filter[state_i]=3, filter[type]=10, filter[updated_after]=1549618720}"

        val params = RequestsPageParamsV3(
                reviewer = accountId,
                requestor = accountId,
                state = RequestState.APPROVED,
                type = ReviewableRequestType.ASSET_CREATE,
                updatedAfter = updated,
                includes = listOf(RequestParamsV3.Includes.REQUEST_DETAILS)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun requestsParamsBuilder() {
        val updated = 1549618720L

        val expected = "{include=request_details, filter[reviewer]=$accountId, filter[requestor]=$accountId, filter[state_i]=3, filter[type]=10, filter[updated_after]=1549618720}"

        val params = RequestsPageParamsV3.Builder()
                .withReviewer(accountId)
                .withRequestor(accountId)
                .withState(RequestState.APPROVED)
                .withType(ReviewableRequestType.ASSET_CREATE)
                .withUpdateAfter(updated)
                .withInclude(RequestParamsV3.Includes.REQUEST_DETAILS)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun salesParams() {
        val expected = "{include=base_asset, filter[owner]=$accountId, filter[max_start_time]=2019-02-08T09:38:40Z, filter[max_end_time]=2019-02-08T09:38:40Z, filter[state]=1, filter[max_hard_cap]=1, filter[min_soft_cap]=10, filter[base_asset]=BTC, filter[sale_type]=2}"

        val params = SalesPageParamsV3(
                baseAsset = "BTC",
                owner = accountId,
                state = SaleState.OPEN,
                maxEndTime = Date(1549618720 * 1000L),
                maxStartTime = Date(1549618720 * 1000L),
                maxHardCap = BigDecimal.ONE,
                minSoftCap = BigDecimal.TEN,
                saleType = SaleType.CROWD_FUNDING,
                includes = listOf(SaleParamsV3.Includes.BASE_ASSET)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun saleParamsBuilder() {
        val expected = "{filter[owner]=$accountId, filter[max_end_time]=2019-02-08T09:38:40Z, filter[state]=1, filter[max_soft_cap]=1, filter[max_hard_cap]=10, filter[base_asset]=BTC, filter[sale_type]=2}"

        val params = SalesPageParamsV3.Builder()
                .withBaseAsset("BTC")
                .withMaxEndTime(Date(1549618720 * 1000L))
                .withMaxHardCap(BigDecimal.TEN)
                .withMaxSoftCap(BigDecimal.ONE)
                .withOwner(accountId)
                .withState(SaleState.OPEN)
                .withSaleType(SaleType.CROWD_FUNDING)
                .withInclude(SaleParamsV3.Includes.BASE_ASSET)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun transactionsParams() {
        val expected = "{filter[account_id]=$accountId}"

        val params = TransactionsPageParams(account = accountId)

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun transactionsParamsBuilder() {
        val expected = "{filter[account_id]=$accountId}"

        val params = TransactionsPageParams.Builder()
                .withAccount(accountId)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun participantEffectsParams() {
        val expected = "{include=operation,operation.details,effect, order=desc, limit=18, cursor=6, page=6, page[number]=6, page[limit]=18, page[order]=desc, filter[account]=$accountId, filter[balance]=superbalance}"

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

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun participantEffectsParamsBuilder() {
        val expected = "{include=operation,operation.details,effect, order=desc, limit=18, cursor=6, page=6, page[number]=6, page[limit]=18, page[order]=desc, filter[account]=$accountId, filter[balance]=superbalance}"

        val params = ParticipantEffectsPageParams.Builder()
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

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun orderBookParams() {
        val expected = "{include=base_asset,quote_asset, order=desc, limit=10, cursor=8, page=8, page[number]=8, page[limit]=10, page[order]=desc, filter[base_asset]=BTC, filter[quote_asset]=ETH, filter[is_buy]=false}"

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

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun oderBookParamsBuilder() {
        val expected = "{include=base_asset,quote_asset, order=desc, limit=10, cursor=8, page=8, page[number]=8, page[limit]=10, page[order]=desc, filter[base_asset]=BTC, filter[quote_asset]=ETH, filter[is_buy]=false}"

        val params = OrderBookPageParams.Builder()
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

        Assert.assertEquals(expected, params.map().toString())
    }
}