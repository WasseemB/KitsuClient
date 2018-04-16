package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class Links(

    @Json(name = "next")
    val next: String? = null,

    @Json(name = "last")
    val last: String? = null,

    @Json(name = "first")
    val first: String? = null
)