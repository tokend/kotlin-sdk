package org.tokend.sdk.api.models

import org.tokend.sdk.api.accounts.model.BalanceDetails
import org.tokend.sdk.api.assets.model.SimpleAsset

open class SimpleBalanceDetails(balanceId: String, balanceString: String,
                                lockedString: String, convertedBalanceString: String,
                                convertedLockedString: String, conversionAsset: String,
                                assetDetails: SimpleAsset?, offers: List<Offer>?
) : BalanceDetails<SimpleAsset>(balanceId, balanceString, lockedString, convertedBalanceString,
        convertedLockedString, conversionAsset, assetDetails, offers)