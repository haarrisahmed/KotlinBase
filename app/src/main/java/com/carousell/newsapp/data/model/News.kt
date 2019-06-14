package com.carousell.newsapp.data.model

/**
 * Created by SangiliPandian C on 14-06-2019.
 */
data class News(
    val id: Int,
    val title: String,
    val description: String,
    val banner_url: String,
    val time_created: String,
    val rank: Int
)