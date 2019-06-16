package com.carousell.newsapp.ui

import android.content.Intent
import android.os.Bundle
import com.carousell.newsapp.R
import com.carousell.newsapp.ui.base.BaseActivity
import com.carousell.newsapp.ui.newsfeed.NewsFeedActivity
import com.carousell.newsapp.utils.Constants
import java.util.*
import kotlin.concurrent.schedule

/**
 * Created by SangiliPandian C on 14-06-2019.
 * we can use this screen to pre-fetch user data, pref, etc.,
 */
class SplashActivity : BaseActivity() {

    override val layoutResourceId = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timer().schedule(Constants.mDelay) {
            navigateToHomePage()
        }
    }

    private fun navigateToHomePage() {
        startActivity(Intent(this, NewsFeedActivity::class.java))
        finish()
    }
}
