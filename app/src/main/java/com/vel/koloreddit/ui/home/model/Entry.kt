package com.vel.koloreddit.ui.home.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

/**
 * Created by Velmurugan on 7/31/2021.
 */
@Root(name = "entry", strict = false)
data class Entry(

    @field:Element(name = "content")
    var content: String? = null,

    @field:Element(required = false, name = "author")
    var authors: Author? = null,

    @field:Element(name = "id")
    var id: String? = null,

    @field:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "updated")
    var updated: String? = null,

    @field:Element(name = "published")
    var published: String? = null
)