package org.tokend.sdk.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ApiDateUtil {
    private val utcTimezone = TimeZone.getTimeZone("UTC")

    /**
     * ISO 8601
     */
    private val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            .utcTimeZone()

    private val supported = arrayOf(
            defaultFormat,
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").utcTimeZone(),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").utcTimeZone(),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").utcTimeZone(),
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss").utcTimeZone(),
            SimpleDateFormat("yyyy-MM-dd HH:mm").utcTimeZone(),
            SimpleDateFormat("yyyy-MM-dd").utcTimeZone()
    )

    /**
     * Parses [Date] from [strDate] using one of supported formats.
     *
     * Method is synchronised!
     *
     * @return parsed [Date].
     *
     * @throws ParseException if format is unknown or data is malformed
     *
     * @see parseDateOrCurrent
     */
    fun tryParseDate(strDate: String): Date = synchronized(this) {
        if (strDate.isEmpty()) {
            throw IllegalArgumentException("\n============\nAh shit, here we go again! If the date is optional, " +
                    "then use null or do not send it at all!\nSend this to backend/JS\n" +
                    "============")
        }

        for (format in supported) {
            try {
                return format.parse(strDate)
            } catch (_: ParseException) {
            }
        }

        throw ParseException(strDate, 0)
    }

    /**
     * Parses [Date] from [strDate] using one of supported formats.
     *
     * @return parsed [Date] or current date in case of failure
     *
     * @see tryParseDate
     */
    fun parseDateOrCurrent(strDate: String): Date = try {
        tryParseDate(strDate)
    } catch (_: Exception) {
        Date()
    }

    /**
     * Format Date to String using the default format (ISO 8601).
     *
     * Method is synchronised!
     */
    fun formatDateForRequest(dateForRequest: Date): String = synchronized(this) {
        return defaultFormat.format(dateForRequest)
    }

    private fun DateFormat.utcTimeZone(): DateFormat = apply {
        timeZone = utcTimezone
    }
}
