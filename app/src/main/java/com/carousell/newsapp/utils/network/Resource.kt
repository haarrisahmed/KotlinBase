package com.carousell.newsapp.utils.network

/**
 * Created by SangiliPandian C on 15-06-2019.
 */

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val message: String? = null
)
