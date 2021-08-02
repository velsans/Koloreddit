package com.vel.koloreddit.api

import com.vel.koloreddit.ui.home.model.Feed
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Velmurugan on 8/02/2021.
 */
interface RedditApi {

    @GET(ServiceURL.popularController + "{filter}/.rss")
    suspend fun pushPopular(
        @Path("filter", encoded=true) filter: String?
    )
            : Response<Feed>
}