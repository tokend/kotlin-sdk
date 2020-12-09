package org.tokend.sdk.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ApiDateUtil {
    private val utcTimezone = TimeZone.getTimeZone("UTC")
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
     * Allow to safety parse Date from String.
     *
     * Method is synchronised!
     *
     * @return parsed value or current Date.
     */
    fun tryParseDate(strDate: String?): Date = synchronized(this) {
        if (strDate.isNullOrEmpty())
            return Date()
        for (format in supported) {
            try {
                return format.parse(strDate)
            } catch (_: ParseException) {
            }
        }
        return Date()
    }

    /**
     * Format Date to String.
     * @return formatted String or empty String if [dateForRequest] is null.
     */
    fun formatDateForRequest(dateForRequest: Date?): String {
        return dateForRequest?.let(defaultFormat::format) ?: ""
    }

    private fun DateFormat.utcTimeZone(): DateFormat = apply {
        timeZone = utcTimezone
    }
}
