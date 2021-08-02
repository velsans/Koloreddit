/*
package com.vel.koloreddit.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vel.koloreddit.room.AppDatabase
import com.vel.koloreddit.ui.home.model.FeedResponce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

*/
/**
 * Created by Velmurugan on 8/1/2021.
 *//*

class FeedViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllFeeds: LiveData<List<FeedResponce>>
    private val feedRepository: FeedRepository

    init {
        val feedDao = AppDatabase.getDatabse(application).feedDao()
        feedRepository = FeedRepository(feedDao)
        readAllFeeds = feedRepository.readAllData
    }

    fun addFeeds(feedResponce: FeedResponce) {
        viewModelScope.launch(Dispatchers.IO) {
            feedRepository.addFeed(feedResponce)
        }
    }
}*/
