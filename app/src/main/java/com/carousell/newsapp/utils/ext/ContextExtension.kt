package com.carousell.newsapp.utils.ext

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

/**
 * Created by SangiliPandian C on 14-06-2019.
 */

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.isNetworkAvailable(): Boolean {
    val manager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return manager.activeNetworkInfo?.isConnected ?: false
}

