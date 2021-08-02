package com.vel.koloreddit.ui.home

import com.vel.koloreddit.api.RetrofitInstance
import com.vel.koloreddit.ui.home.model.Feed
import retrofit2.Response

/**
 * Created by Velmurugan on 31/07/2021.
 */
class HomeRepository() {
    suspend fun pushHome(feefilter:String?): Response<Feed> {
        return RetrofitInstance.api.pushPopular(feefilter)
    }
}