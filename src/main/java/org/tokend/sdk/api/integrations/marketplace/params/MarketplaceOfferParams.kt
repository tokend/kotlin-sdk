package org.tokend.sdk.api.integrations.marketplace.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

class MarketplaceOfferParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val PAYMENT_METHODS = "payment_methods"
        }
    }
}