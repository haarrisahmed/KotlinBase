package com.carousell.newsapp.data.api

import com.carousell.newsapp.db.entity.News
import retrofit2.http.GET

/**
 * Created by SangiliPandian C on 14-06-2019.
 */
interface NewsFeedApi {
    @GET("carousell-interview-assets/android/carousell_news.json")
    suspend fun fetchNews(): List<News>
}