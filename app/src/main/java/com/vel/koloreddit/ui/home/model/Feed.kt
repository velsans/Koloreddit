package com.vel.koloreddit.ui.home.model

import java.io.Serializable
import android.R
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by Velmurugan on 7/31/2021.
 */
@Root(name = "feed", strict = false)
data class Feed(

    @field:Element(name = "id")
    var id: String? = null,

    @field:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "updated")
    var updated: String? = null,

    @field:ElementList(inline = true, name = "entry")
    var entry: List<Entry>? = null
)

