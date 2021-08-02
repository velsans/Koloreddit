package com.vel.koloreddit.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.vel.koloreddit.R

/**
 * Created by Velmurugan on 2/8/2021.
 */
class FeedFilterAdapter(private var feedData: List<String>, private val clickListener: FeedFilterClickListener) :
    RecyclerView.Adapter<FeedFilterAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var feedtitle: TextView = view.findViewById(R.id.tv_operator_name)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.feedtitle.text = feedData[position]
        holder.feedtitle.setOnClickListener {
            clickListener.DenomRecyclerClick(
                holder.feedtitle, feedData[position],position
            )
        }
    }

    override fun getItemCount(): Int {
        return feedData.size
    }
}