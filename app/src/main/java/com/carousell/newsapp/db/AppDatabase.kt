package com.carousell.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carousell.newsapp.db.dao.NewsDao
import com.carousell.newsapp.db.entity.News

/**
 * Created by SangiliPandian C on 15-06-2019.
 */

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}