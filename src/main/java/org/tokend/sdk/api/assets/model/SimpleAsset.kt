package org.tokend.sdk.api.assets.model

import java.math.BigDecimal

/**
 * @see org.tokend.sdk.api.generated.resources.AssetResource
 */
@Deprecated("Asset details are system-specific and will be removed from SDK")
open class SimpleAsset(code: String, ownerAccount: String, policy: Int, details: AssetDetails,
                       issued: BigDecimal, available: BigDecimal, maximum: BigDecimal,
                       type: Long
) : Asset<AssetDetails>(code, ownerAccount, policy, details, issued, available, maximum, type)
