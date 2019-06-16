package com.carousell.newsapp.utils


import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by SangiliPandian C on 16-06-2019.
 */
object CalendarUtils {

    private const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    fun getTimeAgo(timestamp: Long): String {
        val cal = Calendar.getInstance()
        val tz = cal.timeZone
        val sdf = SimpleDateFormat(DATE_FORMAT, getSystemLocale())
        sdf.timeZone = tz
        val localTime = sdf.format(Date(timestamp * 1000))
        return DateUtils.getRelativeTimeSpanString(sdf.parse(localTime).time).toString()
    }

}