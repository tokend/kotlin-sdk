package org.tokend.sdk.api.blobs.model

enum class BlobType(val value: Int) {
    ASSET_DESCRIPTION(1),
    FUND_OVERVIEW(2),
    FUND_UPDATE(4),
    NAV_UPDATE(8),
    FUND_DOCUMENT(16),
    ALPHA(32),
    BRAVO(64),
    CHARLIE(128),
    DELTA(256),
    TOKEN_TERMS(512),
    TOKEN_METRICS(1024),
    KYC_FORM(2048),
    KYC_ID_DOCUMENT(4096),
    KYC_POA(8192),
    IDENTITY_MIND_REJECT(16384);

    companion object {
        @JvmStatic
        fun fromName(name: String): BlobType {
            return BlobType.valueOf(name.toLowerCase())
        }

        @JvmStatic
        fun fromValue(value: Int): BlobType {
            return BlobType.values().find { it.value == value }
                    ?: throw IllegalArgumentException("There is no blob type with value '$value'")
        }
    }
}