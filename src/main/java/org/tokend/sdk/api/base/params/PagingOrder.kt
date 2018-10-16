package org.tokend.sdk.api.base.params

enum class PagingOrder {
    ASC, DESC;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}