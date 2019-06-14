package com.carousell.newsapp.utils

import com.carousell.newsapp.utils.Constants.CONNECT_TIMEOUT
import com.carousell.newsapp.utils.Constants.READ_TIMEOUT
import com.carousell.newsapp.utils.interceptor.ConnectivityInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by SangiliPandian C on 14-06-2019.
 */

fun createNetworkClient(baseUrl: String, debug: Boolean = false) =
    retrofitClient(baseUrl, httpClient(debug))

private fun httpClient(debug: Boolean): OkHttpClient {
    val clientBuilder = OkHttpClient.Builder()
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
        .addInterceptor(ConnectivityInterceptor())

    if (debug) {
        clientBuilder.addNetworkInterceptor(StethoInterceptor())
    }
    return clientBuilder.build()
}

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
