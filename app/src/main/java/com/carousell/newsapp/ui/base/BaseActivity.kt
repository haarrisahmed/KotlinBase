package com.carousell.newsapp.ui.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.carousell.newsapp.R
import com.carousell.newsapp.utils.Constants.CONNECTIVITY_CHANGE
import com.carousell.newsapp.utils.Constants.IS_NETWORK_AVAILABLE
import com.carousell.newsapp.utils.PreferenceHelper
import com.carousell.newsapp.utils.ext.gone
import com.carousell.newsapp.utils.ext.isNetworkAvailable
import com.carousell.newsapp.utils.ext.snack
import com.carousell.newsapp.utils.ext.visible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.error_content.*

/**
 * Created by SangiliPandian C on 14-06-2019.
 */
abstract class BaseActivity : AppCompatActivity() {

    private var currentView: View? = null
    private var lastViewId: Int? = null

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        init(layoutResourceId)
    }

    override fun onResume() {
        super.onResume()
        registerNetworkListener()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(networkChangeReceiver)
    }

    private fun init(layoutId: Int) {
        if (vsContent != null) {
            vsContent.layoutResource = layoutId
            currentView = vsContent.inflate()
        }
        currentView?.let { loadView(it) }
    }

    private fun loadView(currentView: View) {
        lastViewId?.let { findViewById<View>(it)?.gone() }
        lastViewId = currentView.id
        currentView.visible()
    }

    fun hideLoading() = progressbar?.gone()

    fun showLoading() = progressbar?.visible()

    fun isLoading() = progressbar != null

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    protected fun setContentViewVisibility(visibility: Boolean) {
        if (visibility) currentView?.visible() else currentView?.gone()
    }

    protected fun setErrorContentView(errorMsg: String) {
        init(R.layout.error_content)
        tvErrorMsg?.text = errorMsg
    }

    /*Register broadcast receiver */
    private fun registerNetworkListener() {
        val intentFilter = IntentFilter(CONNECTIVITY_CHANGE)
        registerReceiver(networkChangeReceiver, intentFilter)
    }

    /*Network change listener*/
    private val networkChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val isOnline = isNetworkAvailable()
            PreferenceHelper.save(IS_NETWORK_AVAILABLE, isOnline)
            if (!isOnline) {
                currentView?.snack(
                    getString(R.string.network_not_available),
                    Snackbar.LENGTH_SHORT
                ) {}
            }
        }
    }
}