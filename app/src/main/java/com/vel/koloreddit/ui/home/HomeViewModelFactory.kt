package com.vel.koloreddit.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Velmurugan on 31/07/2021.
 */
class HomeViewModelFactory(private val homeRepository: HomeRepository) :
    ViewModelProvider.Factory {
     override fun <T : ViewModel?> create(modelClass: Class<T>): T {
         return HomeViewModel(homeRepository) as T
     }
}