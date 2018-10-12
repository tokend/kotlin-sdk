package org.tokend.sdk.api.sales.model

import org.tokend.sdk.api.accounts.model.AccountsDetailsResponse
import org.tokend.sdk.api.models.NameValue
import java.math.BigDecimal
import java.util.*

open class SimpleSale(id: Long,
                      pagingToken: String,
                      ownerAccount: String,
                      baseAsset: String,
                      defaultQuoteAsset: String,
                      startDate: Date,
                      endDate: Date,
                      softCap: BigDecimal,
                      hardCap: BigDecimal,
                      currentCap: BigDecimal,
                      baseHardCap: BigDecimal,
                      baseCurrentCap: BigDecimal,
                      details: SaleDetails,
                      state: NameValue<Int>,
                      type: NameValue<Int>,
                      statistics: SaleStatistics,
                      ownerDetails: AccountsDetailsResponse.AccountDetails?,
                      quoteAssets: QuoteAssets<SaleQuoteAsset>
) : Sale<SaleDetails, SaleStatistics, SaleQuoteAsset>(id, pagingToken, ownerAccount, baseAsset, defaultQuoteAsset,
        startDate, endDate, softCap, hardCap, currentCap, baseHardCap, baseCurrentCap,
        details, state, type, statistics, quoteAssets, ownerDetails)