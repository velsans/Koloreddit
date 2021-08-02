package com.vel.koloreddit.ui.home.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

/**
 * Created by Velmurugan on 8/1/2021.
 */
@Entity(tableName = "Feeds")
class FeedResponce : Serializable {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    var feedid: String = ""

    var title: String? = ""

    var author: String? = ""

    var date_updated: String? = ""

    var postURL: String? = ""

    var thumbnailURL: String? = ""

    constructor(feedid:String,title: String?, author: String?, date_updated: String?, postURL: String?, thumbnailURL: String?) {
        this.feedid = feedid
        this.title = title
        this.author = author
        this.date_updated = date_updated
        this.postURL = postURL
        this.thumbnailURL = thumbnailURL
    }
}