package org.tokend.sdk.api

internal interface TimeCorrectionProvider {
    fun getTimeCorrection(): Long
}