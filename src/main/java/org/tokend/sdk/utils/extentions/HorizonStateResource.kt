package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.generated.resources.HorizonStateResource
import org.tokend.wallet.NetworkParams
import kotlin.math.log10

fun HorizonStateResource.toNetworkParams(): NetworkParams {
    val precision = log10(precision.toDouble()).toInt()

    return NetworkParams(
            passphrase = this.networkPassphrase,
            precision = precision,
    )
}