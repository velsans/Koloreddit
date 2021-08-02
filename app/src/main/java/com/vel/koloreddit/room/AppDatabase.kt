package com.vel.koloreddit.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vel.koloreddit.ui.home.model.FeedResponce

@Database(entities = [FeedResponce::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao?
}