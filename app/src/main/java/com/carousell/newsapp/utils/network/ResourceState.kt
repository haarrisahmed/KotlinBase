package com.carousell.newsapp.utils.network

/**
 * Created by SangiliPandian C on 15-06-2019.
 */
sealed class ResourceState {
    object LOADING : ResourceState()
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
}
