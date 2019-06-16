package com.carousell.newsapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.carousell.newsapp.CoroutinesTestRule
import com.carousell.newsapp.data.repository.NewsFeedRepository
import com.carousell.newsapp.news
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by SangiliPandian C on 16-06-2019.
 */

class NewsFeedViewModelTest {

    private lateinit var viewModel: NewsFeedViewModel

    private val mockRepo: NewsFeedRepository = mock()

    private val newsList = listOf(news)

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = NewsFeedViewModel(mockRepo)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun refreshNewsFeed() = coroutinesTestRule.testDispatcher.runBlockingTest {
        viewModel = NewsFeedViewModel(mockRepo).also {
            it.refreshNewsFeed()
        }
        Mockito.verify(mockRepo).refreshNewsFeed()
    }
}