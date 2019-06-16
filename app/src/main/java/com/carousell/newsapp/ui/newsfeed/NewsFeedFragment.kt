package com.carousell.newsapp.ui.newsfeed

import android.os.Build.ID
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import com.carousell.newsapp.R
import com.carousell.newsapp.db.entity.News
import com.carousell.newsapp.ui.base.BaseFragment
import com.carousell.newsapp.utils.ext.startRefreshing
import com.carousell.newsapp.utils.ext.stopRefreshing
import com.carousell.newsapp.utils.network.Resource
import com.carousell.newsapp.utils.network.ResourceState
import com.carousell.newsapp.viewmodel.NewsFeedViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_news_feed.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


/**
 * Created by SangiliPandian C on 14-06-2019.
 * List the news feed, we can reuse this fragment in the app.
 */
class NewsFeedFragment : BaseFragment() {

    private val vm: NewsFeedViewModel by viewModel { parametersOf(ID) }

    private val adapter = NewsFeedListAdapter()
    private val snackBar by lazy {
        Snackbar.make(swipeRefreshLayout, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { refreshNewsFeed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        refreshNewsFeed()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.recent -> {
                sortByDate()
                true
            }
            R.id.popular -> {
                sortByPopularity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initUI() {
        newsRecyclerView.adapter = adapter
        vm.news.observe(this, Observer { updateNews(it) })
        swipeRefreshLayout.setOnRefreshListener { refreshNewsFeed() }
    }

    private fun refreshNewsFeed() {
        vm.refreshNewsFeed()
    }

    private fun updateNews(resource: Resource<List<News>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> swipeRefreshLayout.startRefreshing()
                ResourceState.SUCCESS -> swipeRefreshLayout.stopRefreshing()
                ResourceState.ERROR -> swipeRefreshLayout.stopRefreshing()
            }
            it.data?.let { list -> adapter.submitList(list) }
            it.message?.let { snackBar.show() }
        }
    }

    private fun sortByPopularity() {
        adapter.submitList(vm.getPopularNews())
    }

    private fun sortByDate() {
        adapter.submitList(vm.getRecentNews())
    }
}