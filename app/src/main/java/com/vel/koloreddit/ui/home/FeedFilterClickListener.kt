package com.vel.koloreddit.ui.home

import android.view.View

/**
 * Created by Velmurugan on 8/02/2021.
 */
interface FeedFilterClickListener {

    fun DenomRecyclerClick(view: View, filterurl: String, position: Int)
}