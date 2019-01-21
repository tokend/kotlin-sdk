package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

/**
 * Holds policies by bitmask
 */
data class Policies(
        /**
         * Policies parsed as [NameValue] pairs
         */
        @SerializedName("flags")
        @JsonProperty("flags")
        val flags: List<NameValue<Int>>?,

        /**
         * Policies bitmask
         */
        @SerializedName("mask")
        @JsonProperty("mask")
        val mask: Long
) {
    /**
     * @return true if given [policy] is set in bitmask,
     * false otherwise
     *
     * @see mask
     */
    fun have(policy: Long): Boolean {
        return (mask and policy) == policy
    }

    /**
     * @return true if given [policy] is set in bitmask,
     * false otherwise
     *
     * @see mask
     */
    fun have(policy: Int): Boolean {
        return have(policy.toLong())
    }
}