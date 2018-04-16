package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class Large(

    @Json(name = "width")
    val width: Int? = null,

    @Json(name = "height")
    val height: Int? = null
)