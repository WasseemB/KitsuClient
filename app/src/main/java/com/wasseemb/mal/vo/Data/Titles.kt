package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class Titles(
    @Json(name = "en")
    val en: String? = null,
    @Json(name = "ja_jp")
    val jaJp: String? = null,
    @Json(name = "en_us")
    val enUs: String? = null,
    @Json(name = "en_jp")
    val enJp: String? = null
)
