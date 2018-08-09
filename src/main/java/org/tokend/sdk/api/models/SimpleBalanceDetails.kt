package org.tokend.sdk.api.models

open class SimpleBalanceDetails(balanceId: String, balanceString: String,
                                lockedString: String, convertedBalanceString: String,
                                convertedLockedString: String, conversionAsset: String,
                                assetDetails: SimpleAsset?, offers: List<Offer>?
) : BalanceDetails<SimpleAsset>(balanceId, balanceString, lockedString, convertedBalanceString,
        convertedLockedString, conversionAsset, assetDetails, offers)