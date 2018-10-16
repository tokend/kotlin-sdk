package org.tokend.sdk.api.base.params

interface PagingParamsHolder {
    val order: PagingOrder?
    val cursor: String?
    val limit: Int?
}