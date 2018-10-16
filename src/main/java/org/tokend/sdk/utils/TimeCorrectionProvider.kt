package org.tokend.sdk.utils

internal interface TimeCorrectionProvider {
    fun getTimeCorrection(): Long
}