package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class StreamingLinks(

    @Json(name = "links")
    val links: Links? = null
)