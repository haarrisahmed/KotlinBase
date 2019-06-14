package com.carousell.newsapp

import android.app.Application
import com.carousell.newsapp.utils.PreferenceHelper
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created by SangiliPandian C on 14-06-2019.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        //for API debugging
        Stetho.initializeWithDefaults(applicationContext)

        //init crash analytics
        val fabric = Fabric.Builder(this).kits(Crashlytics()).debuggable(true).build()
        Fabric.with(fabric)

        //init timber log
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        //init koin DI
        startKoin { androidContext(this@App) }

        //init pref
        PreferenceHelper.init(applicationContext)
    }
}