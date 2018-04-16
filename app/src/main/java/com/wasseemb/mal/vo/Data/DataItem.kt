package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json
import com.wasseemb.mal.model.DisplayableItem

data class DataItem(

    @Json(name = "relationships")
    val relationships: Relationships? = null,

    @Json(name = "links")
    val links: Links? = null,

    @Json(name = "attributes")
    val attributes: Attributes? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "type")
    val type: String? = null
) : DisplayableItem