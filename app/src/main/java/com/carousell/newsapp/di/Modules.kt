package com.carousell.newsapp.di

import com.carousell.newsapp.BuildConfig
import com.carousell.newsapp.data.api.NewsFeedApi
import com.carousell.newsapp.data.repository.NewsFeedRepository
import com.carousell.newsapp.utils.Constants
import com.carousell.newsapp.utils.createNetworkClient
import com.carousell.newsapp.viewmodel.NewsFeedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by SangiliPandian C on 14-06-2019.
 */

fun injectModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(viewModelModule, repositoryModule)
}

val viewModelModule = module {
    viewModel { NewsFeedViewModel(newsFeedRepository = get()) }
}

val repositoryModule = module {
    single { NewsFeedRepository(api = newsFeedApi) }
}

private val retrofit: Retrofit = createNetworkClient(Constants.BASE_URL, BuildConfig.DEBUG)

private val newsFeedApi: NewsFeedApi = retrofit.create(NewsFeedApi::class.java)