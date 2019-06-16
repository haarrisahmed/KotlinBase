package com.carousell.newsapp.data.repository

import com.carousell.newsapp.data.api.NewsFeedApi
import com.carousell.newsapp.db.dao.NewsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by SangiliPandian C on 14-06-2019.
 */

class NewsFeedRepository(private val newsFeedApi: NewsFeedApi, private val newsDao: NewsDao) {

    suspend fun refreshNewsFeed() {
        withContext(Dispatchers.IO) {
            try {
                val result = newsFeedApi.fetchNews()
                newsDao.insertNews(result)
            } catch (error: Exception) {
                throw Throwable(error)
            }
        }
    }

    fun getNews() = newsDao.getAllNews()

    fun getRecentNews() = newsDao.getRecentNews()

    fun getPopularNews() = newsDao.getPopularNews()
}
