package org.tokend.sdk.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object ApiDateUtil {
    private val supported = arrayOf<DateFormat>(
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS"),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
            SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
            SimpleDateFormat("yyyy-MM-dd HH:mm"),
            SimpleDateFormat("yyyy-MM-dd"))

    /**
     * Allow to safety parse Date from String.
     * @return parsed value or current Date.
     */
    fun tryParseDate(strDate: String?): Date {
        if (strDate.isNullOrEmpty())
            return Date()
        for (format in supported) {
            format.timeZone = TimeZone.getTimeZone("UTC")
            try {
                return format.parse(strDate)
            } catch (ignored: ParseException) {
            }
        }
        return Date()
    }

    /**
     * Format Date to String.
     * @return formatted String or empty String if [dateForRequest] is null.
     */
    fun formatDateForRequest(dateForRequest: Date?): String {
        dateForRequest?.let { date ->
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            format.timeZone = TimeZone.getTimeZone("UTC")
            return format.format(date)
        }
        return ""
    }
}
