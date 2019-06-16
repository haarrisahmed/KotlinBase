package com.carousell.newsapp.ui.newsfeed

import android.os.Bundle
import com.carousell.newsapp.R
import com.carousell.newsapp.di.injectModules
import com.carousell.newsapp.ui.base.BaseActivity
import com.carousell.newsapp.utils.ext.replaceFragment

class NewsFeedActivity : BaseActivity() {

    private val mTAG = NewsFeedActivity::class.java.canonicalName

    override val layoutResourceId = R.layout.activity_news_feed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModules()
        setToolbarTitle(getString(R.string.carousell_news))
        displayFragment()
    }

    private fun displayFragment() {
        replaceFragment(NewsFeedFragment(), mTAG, true, R.id.flNewsFeed)
    }
}
