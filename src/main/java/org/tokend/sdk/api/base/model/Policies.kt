package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Holds policies by bitmask
 */
data class Policies(
        /**
         * Policies parsed as [NameValue] pairs
         */
        @JsonProperty("flags")
        val flags: List<NameValue<Int>>?,

        /**
         * Policies bitmask
         */
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