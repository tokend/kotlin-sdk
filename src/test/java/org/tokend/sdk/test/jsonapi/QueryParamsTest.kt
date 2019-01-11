package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.params.PagingOrder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.requests.model.base.RequestState
import org.tokend.sdk.api.v2.accounts.params.AccountParamsV2
import org.tokend.sdk.api.v2.accounts.params.AccountsPageParamsV2
import org.tokend.sdk.api.v2.assetpairs.params.AssetPairParams
import org.tokend.sdk.api.v2.assetpairs.params.AssetPairsPageParams
import org.tokend.sdk.api.v2.assets.params.AssetParams
import org.tokend.sdk.api.v2.assets.params.AssetsPageParams
import org.tokend.sdk.api.v2.balances.params.BalanceParams
import org.tokend.sdk.api.v2.balances.params.BalancesPageParams
import org.tokend.sdk.api.v2.fees.params.FeeParamsV2
import org.tokend.sdk.api.v2.fees.params.FeesPageParamsV2
import org.tokend.sdk.api.v2.offers.params.OfferParamsV2
import org.tokend.sdk.api.v2.offers.params.OffersPageParamsV2
import org.tokend.sdk.api.v2.operations.params.OperationParamsV2
import org.tokend.sdk.api.v2.operations.params.OperationsPageParamsV2
import org.tokend.sdk.api.v2.requests.params.AssetRequestPageParams
import org.tokend.sdk.api.v2.requests.params.RequestParamsV2
import org.tokend.sdk.api.v2.requests.params.RequestsPageParamsV2
import org.tokend.sdk.api.v2.sales.params.SaleParamsV2
import org.tokend.sdk.api.v2.sales.params.SalesPageParamsV2
import org.tokend.sdk.api.v2.transactions.params.TransactionsPageParams
import org.tokend.sdk.test.Config
import org.tokend.wallet.xdr.*
import java.math.BigDecimal

class QueryParamsTest {

    private val accountId = Config.ADMIN_ACCOUNT.accountId

    @Test
    fun accountsParams() {
        val expected = "{include=external_accounts, account_type=5, signer_type=2048, is_blocked=false}"

        val params = AccountsPageParamsV2(
                accountTypes = listOf(AccountType.NOT_VERIFIED),
                signerTypes = listOf(SignerType.ACCOUNT_MANAGER),
                isBlocked = false,
                include = listOf(AccountParamsV2.Includes.EXTERNAL_ACCOUNTS)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun accountsParamsBuilder() {
        val expected = "{include=external_accounts, account_type=5, signer_type=2048, is_blocked=false}"

        val params = AccountsPageParamsV2.Builder()
                .withAccountTypes(AccountType.NOT_VERIFIED)
                .withSignerTypes(SignerType.ACCOUNT_MANAGER)
                .withIsBlocked(false)
                .withInclude(AccountParamsV2.Includes.EXTERNAL_ACCOUNTS)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun assetPairsParams() {
        val expected = "{include=base_asset, policy=4, base_asset=BTC, quote_asset=ETH}"

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
        val expected = "{include=base_asset, policy=4, base_asset=BTC, quote_asset=ETH}"

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
        val expected = "{include=owner, policy=2}"

        val params = AssetsPageParams(
                policies = listOf(AssetPolicy.BASE_ASSET),
                include = listOf(AssetParams.Includes.OWNER)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun assetParamsBuilder() {
        val expected = "{include=owner, policy=2}"

        val params = AssetsPageParams.Builder()
                .withPolicies(AssetPolicy.BASE_ASSET)
                .withInclude(AssetParams.Includes.OWNER)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun balancesParams() {
        val expected = "{include=account, asset=ETH, account=$accountId}"

        val params = BalancesPageParams(
                asset = "ETH",
                account = accountId,
                include = listOf(BalanceParams.Includes.ACCOUNT)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun balanceParamsBuilder() {
        val expected = "{include=account, asset=ETH, account=$accountId}"

        val params = BalancesPageParams.Builder()
                .withAsset("ETH")
                .withAccount(accountId)
                .withInclude(BalanceParams.Includes.ACCOUNT)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun feesParams() {
        val expected = "{include=account, asset=ETH, fee_type=0, subtype=1, account_id=$accountId, account_type=10, lower_bound=1, upper_bound=10}"

        val params = FeesPageParamsV2(
                asset = "ETH",
                type = FeeType.PAYMENT_FEE,
                subtype = 1,
                account = accountId,
                accountType = AccountType.VERIFIED,
                lowerBound = BigDecimal.ONE,
                upperBound = BigDecimal.TEN,
                include = listOf(FeeParamsV2.Includes.ACCOUNT)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun feesParamsBuilder() {
        val expected = "{include=account, asset=ETH, fee_type=0, subtype=1, account_id=$accountId, account_type=10, lower_bound=1, upper_bound=10}"

        val params = FeesPageParamsV2.Builder()
                .withAsset("ETH")
                .withType(FeeType.PAYMENT_FEE)
                .withSubtype(1)
                .withAccount(accountId)
                .withAccountType(AccountType.VERIFIED)
                .withLowerBound(BigDecimal.ONE)
                .withUpperBound(BigDecimal.TEN)
                .withInclude(FeeParamsV2.Includes.ACCOUNT)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun offersParams() {
        val expected = "{include=base_asset, base_balance=10, quote_balance=15, base_asset=ETH, quote_asset=BTC, owner=$accountId, order_book_id=${Long.MAX_VALUE}}"

        val params = OffersPageParamsV2(
                baseBalance = "10",
                quoteBalance = "15",
                baseAsset = "ETH",
                quoteAsset = "BTC",
                ownerAccount = accountId,
                orderBookId = Long.MAX_VALUE,
                include = listOf(OfferParamsV2.Includes.BASE_ASSET)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun offersParamsBuilder() {
        val expected = "{include=base_asset, base_balance=10, quote_balance=15, base_asset=ETH, quote_asset=BTC, owner=$accountId, order_book_id=${Long.MAX_VALUE}}"

        val params = OffersPageParamsV2.Builder()
                .withBaseBalance("10")
                .withQuoteBalance("15")
                .withBaseAsset("ETH")
                .withQuoteAsset("BTC")
                .withOwnerAccount(accountId)
                .withOrderBoolId(Long.MAX_VALUE)
                .withInclude(OfferParamsV2.Includes.BASE_ASSET)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun requestsParams() {
        val updated = System.currentTimeMillis()

        val expected = "{include=request_details, reviewer=$accountId, requestor=$accountId, state_i=3, type=0, updated_after=$updated}"

        val params = RequestsPageParamsV2(
                reviewer = accountId,
                requestor = accountId,
                state = RequestState.APPROVED,
                type = ReviewableRequestType.ASSET_CREATE,
                updatedAfter = updated,
                includes = listOf(RequestParamsV2.Includes.REQUEST_DETAILS)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun requestsParamsBuilder() {
        val updated = System.currentTimeMillis()

        val expected = "{include=request_details, reviewer=$accountId, requestor=$accountId, state_i=3, type=0, updated_after=$updated}"

        val params = RequestsPageParamsV2.Builder()
                .withReviewer(accountId)
                .withRequestor(accountId)
                .withState(RequestState.APPROVED)
                .withType(ReviewableRequestType.ASSET_CREATE)
                .withUpdateAfter(updated)
                .withInclude(RequestParamsV2.Includes.REQUEST_DETAILS)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun salesParams() {
        val expected = "{include=base_asset, name=BestNameEver, base_asset=BTC, owner=$accountId, open_only=true, upcoming=true, voting=true, promotions=true, sort_by=2}"

        val params = SalesPageParamsV2(
                name = "BestNameEver",
                baseAsset = "BTC",
                ownerAccount = accountId,
                openOnly = true,
                upcoming = true,
                voting = true,
                promotions = true,
                sortBy = SalesPageParamsV2.SortCriteria.MOST_FUNDED,
                includes = listOf(SaleParamsV2.Includes.BASE_ASSET)
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun saleParamsBuilder() {
        val expected = "{include=base_asset, name=BestNameEver, base_asset=BTC, owner=$accountId, open_only=true, upcoming=true, voting=true, promotions=true, sort_by=2}"

        val params = SalesPageParamsV2.Builder()
                .withName("BestNameEver")
                .withBaseAsset("BTC")
                .withOwnerAccount(accountId)
                .withOpenOnly(true)
                .withUpcoming(true)
                .withVoting(true)
                .withPromotions(true)
                .withSortBy(SalesPageParamsV2.SortCriteria.MOST_FUNDED)
                .withInclude(SaleParamsV2.Includes.BASE_ASSET)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun transactionsParams() {
        val expected = "{account_id=$accountId}"

        val params = TransactionsPageParams(account = accountId)

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun transactionsParamsBuilder() {
        val expected = "{account_id=$accountId}"

        val params = TransactionsPageParams.Builder()
                .withAccount(accountId)
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun operationsParams() {
        val expected = "{include=operation_details,source, tx_id=txid, account_id=$accountId, reference=reference, account_type=6, states=7, subset=payments, order=desc, limit=42, cursor=10, page=10}"

        val params = OperationsPageParamsV2(
                transaction = "txid",
                account = accountId,
                reference = "reference",
                accountType = AccountType.SYNDICATE,
                subset = OperationsPageParamsV2.Subsets.PAYMENTS,
                states = listOf(1, 2, 4),
                include = listOf(
                        OperationParamsV2.Includes.OPERATION_DETAILS,
                        OperationParamsV2.Includes.SOURCE
                ),
                pagingParams = PagingParamsV2(
                        order = PagingOrder.DESC,
                        limit = 42,
                        page = "10"
                )
        )

        Assert.assertEquals(expected, params.map().toString())
    }

    @Test
    fun operationsParamsBuilder() {
        val expected = "{include=operation_details,source, tx_id=txid, account_id=$accountId, reference=reference, account_type=6, states=7, subset=payments, order=desc, limit=42, cursor=10, page=10}"

        val params = OperationsPageParamsV2.Builder()
                .withTransaction("txid")
                .withAccount(accountId)
                .withReference("reference")
                .withAccountType(AccountType.SYNDICATE)
                .withSubset(OperationsPageParamsV2.Subsets.PAYMENTS)
                .withStates(1, 2, 4)
                .withPagingParams(
                        PagingParamsV2(
                                order = PagingOrder.DESC,
                                limit = 42,
                                page = "10"
                        )
                )
                .withInclude(
                        OperationParamsV2.Includes.OPERATION_DETAILS,
                        OperationParamsV2.Includes.SOURCE
                )
                .build()

        Assert.assertEquals(expected, params.map().toString())
    }
}