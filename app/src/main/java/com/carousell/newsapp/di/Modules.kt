package com.carousell.newsapp.di

import androidx.room.Room
import com.carousell.newsapp.BuildConfig
import com.carousell.newsapp.data.api.NewsFeedApi
import com.carousell.newsapp.data.repository.NewsFeedRepository
import com.carousell.newsapp.db.AppDatabase
import com.carousell.newsapp.utils.Constants
import com.carousell.newsapp.utils.Constants.DB_NAME
import com.carousell.newsapp.utils.network.createNetworkClient
import com.carousell.newsapp.viewmodel.NewsFeedViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by SangiliPandian C on 14-06-2019.
 */

fun injectModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(viewModelModule, repositoryModule, roomModule))
}

val viewModelModule = module {
    viewModel { NewsFeedViewModel(newsFeedRepository = get()) }
}

val repositoryModule = module {
    single { NewsFeedRepository(newsFeedApi = newsFeedApi, newsDao = get()) }
}

val roomModule = module {
    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries() // TODO should allow CRUD operation on background thread
            .build()
    }
    single { get<AppDatabase>().newsDao() }
}

private val retrofit: Retrofit =
    createNetworkClient(Constants.BASE_URL, BuildConfig.DEBUG)

private val newsFeedApi: NewsFeedApi = retrofit.create(NewsFeedApi::class.java)