package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class PosterImage(

    @Json(name = "small")
    val small: String? = null,

    @Json(name = "original")
    val original: String? = null,

    @Json(name = "large")
    val large: String? = null,

    @Json(name = "tiny")
    val tiny: String? = null,

    @Json(name = "meta")
    val meta: Meta? = null,

    @Json(name = "medium")
    val medium: String? = null
)