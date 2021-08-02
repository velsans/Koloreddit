package com.vel.koloreddit.ui.home.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root
import java.io.Serializable

/**
 * Created by Velmurugan on 7/31/2021.
 */
@Root(name = "author", strict = false)
class Author {
    @field:Element(name = "name")
    var name: String? = null

    @field:Element(name = "uri")
    var uri: String? = null
}