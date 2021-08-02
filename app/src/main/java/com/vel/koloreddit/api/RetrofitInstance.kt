package com.vel.koloreddit.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Created by Velmurugan on 31/07/2021.
 */
object RetrofitInstance {
    private val client = OkHttpClient.Builder().build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ServiceURL.BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client)
            .build()
    }

    val api: RedditApi by lazy {
        retrofit.create(RedditApi::class.java)
    }

}