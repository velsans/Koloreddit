package com.vel.koloreddit.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import coil.transform.CircleCropTransformation
import com.vel.koloreddit.R
import com.vel.koloreddit.ui.home.model.FeedResponce
import kotlinx.android.synthetic.main.feed_infliator.view.*
import java.io.File

/**
 * Created by Velmurugan on 8/01/2021.
 */
class FeedsAdapter(private var feedData: List<FeedResponce>) :
    RecyclerView.Adapter<FeedsAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var feedImage: ImageView = view.findViewById(R.id.media_image)
        var feedtitle: TextView = view.findViewById(R.id.primary_text)
        var feedvideo: VideoView = view.findViewById(R.id.feedvid)
        var feedurl: TextView = view.findViewById(R.id.sub_text)

    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_infliator, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val feedItem = feedData[position]
        if (feedItem.thumbnailURL != null) {
            val filename: String = feedItem.thumbnailURL!!.substring(feedItem.thumbnailURL!!.length - 3)
            if (filename == "mp4") {
                holder.feedImage.media_image.visibility = View.GONE
                holder.feedvideo.feedvid.visibility = View.VISIBLE
            } else {
                holder.feedImage.media_image.visibility = View.VISIBLE
                holder.feedvideo.feedvid.visibility = View.INVISIBLE
                holder.feedImage.media_image.load(feedItem.thumbnailURL)
                {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_background)
                    error(R.mipmap.eror)
                }
            }
        }
        holder.feedtitle.text = feedItem.title
        holder.feedurl.text = feedItem.thumbnailURL
    }

    override fun getItemCount(): Int {
        return feedData.size
    }
}