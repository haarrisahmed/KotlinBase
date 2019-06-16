package com.carousell.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.carousell.newsapp.data.repository.NewsFeedRepository
import com.carousell.newsapp.db.entity.News
import com.carousell.newsapp.ui.base.BaseViewModel
import com.carousell.newsapp.utils.ext.setError
import com.carousell.newsapp.utils.ext.setLoading
import com.carousell.newsapp.utils.ext.setSuccess
import com.carousell.newsapp.utils.network.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by SangiliPandian C on 14-06-2019.
 */
class NewsFeedViewModel(private val newsFeedRepository: NewsFeedRepository) : BaseViewModel() {

    val news = MutableLiveData<Resource<List<News>>>()

    fun refreshNewsFeed() {
        news.setLoading()
        launchDataLoad {}
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                newsFeedRepository.refreshNewsFeed()
                news.setSuccess(newsFeedRepository.getNews())
                block()
            } catch (error: Exception) {
                news.setError(error.message)
            }
        }
    }

    fun getRecentNews() = newsFeedRepository.getRecentNews()

    fun getPopularNews() = newsFeedRepository.getPopularNews()

}