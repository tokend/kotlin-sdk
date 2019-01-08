package org.tokend.sdk.utils

import java.math.BigDecimal

object BigDecimalUtil {
    /**
     * Allows to safely parse BigDecimal from String with default value (0 by default)
     * @return parsed value or [default] if it can't be parsed
     */
    fun valueOf(stringValue: String?, defaultValue: BigDecimal = BigDecimal.ZERO): BigDecimal {
        return try {
            if (stringValue.isNullOrBlank())
                defaultValue
            else
                BigDecimal(stringValue)
        } catch (e: NumberFormatException) {
            defaultValue
        }
    }

    fun toPlainString(amount: BigDecimal?): String {
        return stripTrailingZeros(amount).toPlainString()
    }

    fun scaleAmount(value: BigDecimal, scale: Int): BigDecimal {
        val res = value.setScale(scale, BigDecimal.ROUND_DOWN)
        return stripTrailingZeros(res)
    }

    fun stripTrailingZeros(value: BigDecimal?): BigDecimal {
        return if (value == null || value.signum() == 0) {
            BigDecimal.ZERO
        } else {
            value.stripTrailingZeros()
        }
    }
}