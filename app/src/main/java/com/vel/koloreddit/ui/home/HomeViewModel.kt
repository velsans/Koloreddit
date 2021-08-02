package com.vel.koloreddit.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.vel.koloreddit.room.FeedDao
import com.vel.koloreddit.ui.home.model.Feed
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class HomeViewModel(private val homeRepository: HomeRepository) :
    ViewModel() {
    private val _home = MutableLiveData<Response<Feed>>()
    val home: LiveData<Response<Feed>>
        get() = _home

    fun pushPopular(feefilter:String?) {
        viewModelScope.launch {
            try {
                val response = homeRepository.pushHome(feefilter)
                _home.value = response
            } catch (e: Exception) {
                Log.d("Exception", e.toString())
            }
        }
    }


}
