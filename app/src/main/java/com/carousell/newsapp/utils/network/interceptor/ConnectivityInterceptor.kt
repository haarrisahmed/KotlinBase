package com.carousell.newsapp.utils.network.interceptor

import com.carousell.newsapp.utils.Constants.IS_NETWORK_AVAILABLE
import com.carousell.newsapp.utils.PreferenceHelper
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by SangiliPandian C on 14-06-2019.
 */

class ConnectivityInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val isOnline = PreferenceHelper.get(IS_NETWORK_AVAILABLE, false)
        return if (!isOnline) {
            throw NoConnectivityException()
        } else {
            chain.proceed(chain.request())
        }
    }
}

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No network available, please check your WiFi or Data connection"
}