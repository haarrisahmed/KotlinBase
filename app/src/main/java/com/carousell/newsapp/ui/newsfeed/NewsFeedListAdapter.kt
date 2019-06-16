package com.carousell.newsapp.ui.newsfeed

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carousell.newsapp.R
import com.carousell.newsapp.db.entity.News
import com.carousell.newsapp.utils.CalendarUtils.getTimeAgo
import com.carousell.newsapp.utils.ext.inflate
import com.carousell.newsapp.utils.ext.loadImage
import kotlinx.android.synthetic.main.item_news_feed.view.*

/**
 * Created by SangiliPandian C on 16-06-2019.
 */

class NewsFeedListAdapter : ListAdapter<News, NewsFeedListAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_news_feed)) {
        fun bind(item: News) {
            itemView.image.loadImage(item.image)
            itemView.tvTitle.text = item.title
            itemView.tvSubTitle.text = item.subTitle
            itemView.tvTime.text = getTimeAgo(item.time)
        }
    }
}

private class NewsDiffCallback : DiffUtil.ItemCallback<News>() {
    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean =
        oldItem == newItem
}