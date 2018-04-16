package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class Medium(

    @Json(name = "width")
    val width: Any? = null,

    @Json(name = "height")
    val height: Any? = null
)