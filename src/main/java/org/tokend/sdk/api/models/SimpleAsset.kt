package org.tokend.sdk.api.models

import org.tokend.sdk.api.models.sale.SimpleSale
import java.math.BigDecimal

open class SimpleAsset(code: String, ownerAccount: String, policy: Int, details: Details?,
                       issued: BigDecimal, available: BigDecimal, maximum: BigDecimal,
                       sales: List<SimpleSale>?
) : Asset<SimpleSale>(code, ownerAccount, policy, details, issued, available, maximum, sales)
