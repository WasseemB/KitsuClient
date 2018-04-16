package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class KitsuResponse(

    @Json(name = "data")
    val data: List<DataItem?>? = null,

    @Json(name = "meta")
    val meta: Meta? = null,

    @Json(name = "links")
    val links: Links? = null
)