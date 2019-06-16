package com.carousell.newsapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by SangiliPandian C on 14-06-2019.
 */
@Entity
data class News(
    @PrimaryKey
    val id: Int,
    val title: String,
    @SerializedName(value = "description")
    val subTitle: String,
    @SerializedName(value = "banner_url")
    val image: String,
    @SerializedName(value = "time_created")
    val time: Long,
    val rank: Int
)