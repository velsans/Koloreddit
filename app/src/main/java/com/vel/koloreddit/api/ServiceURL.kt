package com.vel.koloreddit.api

class ServiceURL {
    /*URL's*/
    companion object {
        const val LOCAL_URL = "https://www.reddit.com/r/"
        const val LIVE_URL = "https://www.reddit.com/r/"
        const val popularController="popular/"


        /*Final Url*/
        @JvmField
        val BASE_URL = Local_server()

        @JvmField
        var Local_live: String? = null

        @JvmField
        var Local_live_Flag: Boolean? = null

        private fun Local_server(): String {
            Local_live = "Local"
            Local_live_Flag = false
            return LOCAL_URL
        }

        private fun Live_server(): String {
            Local_live = "Live"
            Local_live_Flag = true
            return LIVE_URL
        }
    }
}