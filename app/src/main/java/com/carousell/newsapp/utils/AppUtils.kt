package com.carousell.newsapp.utils

import android.os.Build
import com.carousell.newsapp.App
import java.util.*

/**
 * Created by SangiliPandian C on 14-06-2019.
 */

object AppUtils {
    
    @Suppress("DEPRECATION")
    fun getSystemLocale(): Locale {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> App.instance.resources.configuration.locales[0]
            else -> App.instance.resources.configuration.locale
        }

    }
}