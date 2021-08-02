package com.vel.koloreddit.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vel.koloreddit.ui.home.model.FeedResponce

/**
 * Created by Velmurugan on 8/01/2021.
 */
@Dao
interface FeedDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFeed(feedResponce: FeedResponce)

    @Query("SELECT * from Feeds ORDER BY 1 DESC")
    fun readAllfeed(): List<FeedResponce>

    @Update
    fun updateFeed(feedResponce: FeedResponce)
}