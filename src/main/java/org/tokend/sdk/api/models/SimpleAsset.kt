package org.tokend.sdk.api.models

import org.tokend.sdk.api.models.sale.SimpleSale
import java.math.BigDecimal

open class SimpleAsset(code: String, ownerAccount: String, policy: Int, details: AssetDetails?,
                       issued: BigDecimal, available: BigDecimal, maximum: BigDecimal,
                       sales: List<SimpleSale>?
) : Asset<SimpleSale, AssetDetails>(code, ownerAccount, policy, details, issued, available, maximum, sales)
