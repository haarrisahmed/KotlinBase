package com.carousell.newsapp.utils


import java.util.*
import com.carousell.newsapp.utils.CalendarUtils.WEEK_MILLIS as WEEK_MILLIS1


/**
 * Created by SangiliPandian C on 16-06-2019.
 */
object CalendarUtils {

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS
    private const val WEEK_MILLIS = 7 * DAY_MILLIS

    fun getTimeAgoFormat(time: Long): String {
        var mTime = time
        if (mTime < 1000000000000L) {
            mTime *= 1000
        }
        val now = System.currentTimeMillis()
        if (mTime > now || mTime <= 0) {
            return ""
        }
        val diff = now - mTime
        return when {
            diff < MINUTE_MILLIS -> "just now"
            diff < 2 * MINUTE_MILLIS -> "a minute ago"
            diff < 50 * MINUTE_MILLIS -> (diff / MINUTE_MILLIS).toString() + " minutes ago"
            diff < 90 * MINUTE_MILLIS -> "an hour ago"
            diff < 24 * HOUR_MILLIS -> (diff / HOUR_MILLIS).toString() + " hours ago"
            diff < 48 * HOUR_MILLIS -> "yesterday"
            diff / WEEK_MILLIS > 52 -> getDiffYears(mTime)
            diff / WEEK_MILLIS > 4 -> getDiffMonths(mTime)
            diff / WEEK_MILLIS > 1 -> getDiffWeeks(mTime)
            diff / DAY_MILLIS > 5 -> "5 days ago"
            else -> (diff / DAY_MILLIS).toString() + " days ago"
        }
    }


    private fun getDiffWeeks(time: Long): String {
        val startCalendar = startDate(time)
        val endCalendar = endDate()
        val diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)
        val diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH)
        val weeksDiff =
            diffMonth * 52 + endCalendar.get(Calendar.WEEK_OF_YEAR) - startCalendar.get(Calendar.WEEK_OF_YEAR)
        return if (weeksDiff > 1) "$weeksDiff weeks ago" else "$weeksDiff week ago"
    }

    private fun getDiffMonths(time: Long): String {
        val startCalendar = startDate(time)
        val endCalendar = endDate()
        val diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)
        val diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH)
        return if (diffMonth > 1) "$diffMonth months ago" else "$diffMonth month ago"
    }

    private fun getDiffYears(time: Long): String {
        val startCalendar = startDate(time)
        val endCalendar = endDate()
        val diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR)
        return if (diffYear > 1) "$diffYear years ago" else "$diffYear year ago"
    }

    private fun startDate(time: Long): GregorianCalendar {
        val startCalendar = GregorianCalendar()
        startCalendar.time = Date(time)
        return startCalendar
    }

    private fun endDate(): GregorianCalendar {
        val endCalendar = GregorianCalendar()
        endCalendar.time = Date(Calendar.getInstance().timeInMillis)
        return endCalendar
    }

}