package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class Dimensions(

    @Json(name = "small")
    val small: Small? = null,

    @Json(name = "large")
    val large: Large? = null,

    @Json(name = "tiny")
    val tiny: Tiny? = null,

    @Json(name = "medium")
    val medium: Medium? = null
)