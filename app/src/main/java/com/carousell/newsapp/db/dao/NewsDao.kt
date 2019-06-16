package com.carousell.newsapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carousell.newsapp.db.entity.News

/**
 * Created by SangiliPandian C on 15-06-2019.
 */

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(result: List<News>)

    @Query("Select *from News order by time asc")
    fun getAllNews(): List<News>

    @Query("Select *from News order by time desc")
    fun getRecentNews(): List<News>

    @Query("Select *from News order by rank asc")
    fun getPopularNews(): List<News>
}