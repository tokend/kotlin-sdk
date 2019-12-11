@file:JvmName("IngesterStateUtils")

package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.ingester.generated.resources.IngesterStateResource
import org.tokend.wallet.NetworkParams

fun IngesterStateResource.toNetworkParams() = NetworkParams(
        passphrase = this.networkPassphrase,
        precision = Math.log10(this.precision.toDouble()).toInt(),
        timeOffsetSeconds = (this.currentTimeUnix - System.currentTimeMillis() / 1000L).toInt()
)