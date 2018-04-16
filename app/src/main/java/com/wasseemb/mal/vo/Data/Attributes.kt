package com.wasseemb.mal.vo.Data

import com.squareup.moshi.Json

data class Attributes(

    @Json(name = "nextRelease")
    val nextRelease: Any? = null,

    @Json(name = "endDate")
    val endDate: String? = null,

    @Json(name = "episodeCount")
    val episodeCount: Int? = null,

    @Json(name = "ratingRank")
    val ratingRank: Int? = null,

    @Json(name = "posterImage")
    val posterImage: PosterImage? = null,

    @Json(name = "createdAt")
    val createdAt: String? = null,

    @Json(name = "subtype")
    val subtype: String? = null,

    @Json(name = "youtubeVideoId")
    val youtubeVideoId: String? = null,

    @Json(name = "averageRating")
    val averageRating: String? = null,

    @Json(name = "coverImage")
    val coverImage: CoverImage? = null,

    @Json(name = "ratingFrequencies")
    val ratingFrequencies: RatingFrequencies? = null,

    @Json(name = "showType")
    val showType: String? = null,

    @Json(name = "abbreviatedTitles")
    val abbreviatedTitles: Any? = null,

    @Json(name = "slug")
    val slug: String? = null,

    @Json(name = "episodeLength")
    val episodeLength: Int? = null,

    @Json(name = "updatedAt")
    val updatedAt: String? = null,

    @Json(name = "nsfw")
    val nsfw: Boolean? = null,

    @Json(name = "synopsis")
    val synopsis: String? = null,

    @Json(name = "titles")
    val titles: Titles? = null,

    @Json(name = "ageRating")
    val ageRating: String? = null,

    @Json(name = "favoritesCount")
    val favoritesCount: Int? = null,

    @Json(name = "coverImageTopOffset")
    val coverImageTopOffset: Int? = null,

    @Json(name = "canonicalTitle")
    val canonicalTitle: String? = null,

    @Json(name = "tba")
    val tba: Any? = null,

    @Json(name = "userCount")
    val userCount: Int? = null,

    @Json(name = "popularityRank")
    val popularityRank: Int? = null,

    @Json(name = "ageRatingGuide")
    val ageRatingGuide: String? = null,

    @Json(name = "startDate")
    val startDate: String? = null,

    @Json(name = "status")
    val status: String? = null
)