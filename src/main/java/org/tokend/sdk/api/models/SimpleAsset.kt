package org.tokend.sdk.api.models

import java.math.BigDecimal

open class SimpleAsset(code: String, ownerAccount: String, policy: Int, details: AssetDetails,
                       issued: BigDecimal, available: BigDecimal, maximum: BigDecimal
) : Asset<AssetDetails>(code, ownerAccount, policy, details, issued, available, maximum)
