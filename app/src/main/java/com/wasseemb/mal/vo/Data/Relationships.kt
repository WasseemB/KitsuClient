package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class Relationships(

    @Json(name = "castings")
    val castings: Castings? = null,

    @Json(name = "mappings")
    val mappings: Mappings? = null,

    @Json(name = "animeStaff")
    val animeStaff: AnimeStaff? = null,

    @Json(name = "reviews")
    val reviews: Reviews? = null,

    @Json(name = "installments")
    val installments: Installments? = null,

    @Json(name = "genres")
    val genres: Genres? = null,

    @Json(name = "animeCharacters")
    val animeCharacters: AnimeCharacters? = null,

    @Json(name = "mediaRelationships")
    val mediaRelationships: MediaRelationships? = null,

    @Json(name = "animeProductions")
    val animeProductions: AnimeProductions? = null,

    @Json(name = "categories")
    val categories: Categories? = null,

    @Json(name = "streamingLinks")
    val streamingLinks: StreamingLinks? = null,

    @Json(name = "episodes")
    val episodes: Episodes? = null
)