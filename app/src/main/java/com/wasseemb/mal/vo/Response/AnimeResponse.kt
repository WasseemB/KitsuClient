package com.wasseemb.mal.vo.Response

import com.squareup.moshi.Json
import com.wasseemb.mal.vo.Data.Dimensions
import com.wasseemb.mal.vo.Data.Episodes
import com.wasseemb.mal.vo.Data.Genres
import com.wasseemb.mal.vo.Data.Installments
import com.wasseemb.mal.vo.Data.Large
import com.wasseemb.mal.vo.Data.Links
import com.wasseemb.mal.vo.Data.MediaRelationships
import com.wasseemb.mal.vo.Data.Medium
import com.wasseemb.mal.vo.Data.PosterImage
import com.wasseemb.mal.vo.Data.Small
import com.wasseemb.mal.vo.Data.Tiny
import com.wasseemb.mal.vo.Data.Titles


/**
 * Created by Wasseem on 23/03/2018.
 */

data class AnimeResponse(
    @Json(name = "data") val data: Data
)

data class AnimeGenresResponse(
    @Json(name = "data") val data: List<Data>,
    @Json(name = "meta") val meta: Meta,
    @Json(name = "links") val links: Links
)

data class Data(
    @Json(name = "id") val id: String,
    @Json(name = "type") val type: String,
    @Json(name = "links") val links: Links,
    @Json(name = "attributes") val attributes: Attributes,
    @Json(name = "relationships") val relationships: Relationships
)


data class Attributes(
    @Json(name = "createdAt") val createdAt: String,
    @Json(name = "updatedAt") val updatedAt: String,
    @Json(name = "name") val name: String,
    @Json(name = "slug") val slug: String,
    @Json(name = "synopsis") val synopsis: String,
    @Json(name = "coverImageTopOffset") val coverImageTopOffset: Int,
    @Json(name = "titles") val titles: Titles,
    @Json(name = "canonicalTitle") val canonicalTitle: String,
    @Json(name = "abbreviatedTitles") val abbreviatedTitles: List<String>,
    @Json(name = "averageRating") val averageRating: String,
    @Json(name = "ratingFrequencies") val ratingFrequencies: RatingFrequencies,
    @Json(name = "userCount") val userCount: Int,
    @Json(name = "favoritesCount") val favoritesCount: Int,
    @Json(name = "startDate") val startDate: String,
    @Json(name = "endDate") val endDate: String,
    @Json(name = "popularityRank") val popularityRank: Int,
    @Json(name = "ratingRank") val ratingRank: Int? = null,
    @Json(name = "ageRating") val ageRating: String,
    @Json(name = "ageRatingGuide") val ageRatingGuide: String,
    @Json(name = "subtype") val subtype: String,
    @Json(name = "status") val status: String,
    @Json(name = "tba") val tba: Any,
    @Json(name = "posterImage") val posterImage: PosterImage,
    @Json(name = "coverImage") val coverImage: Any,
    @Json(name = "episodeCount") val episodeCount: Int? = null,
    @Json(name = "episodeLength") val episodeLength: Int? = null,
    @Json(name = "youtubeVideoId") val youtubeVideoId: String,
    @Json(name = "showType") val showType: String,
    @Json(name = "nsfw") val nsfw: Boolean
)

data class Titles(
    @Json(name = "en") val en: String,
    @Json(name = "en_jp") val enJp: String,
    @Json(name = "ja_jp") val jaJp: String,
    @Json(name = "en_us") val enUs: String
)

data class RatingFrequencies(
    @Json(name = "2") val x2: String,
    @Json(name = "3") val x3: String,
    @Json(name = "4") val x4: String,
    @Json(name = "5") val x5: String,
    @Json(name = "6") val x6: String,
    @Json(name = "7") val x7: String,
    @Json(name = "8") val x8: String,
    @Json(name = "9") val x9: String,
    @Json(name = "10") val x10: String,
    @Json(name = "11") val x11: String,
    @Json(name = "12") val x12: String,
    @Json(name = "13") val x13: String,
    @Json(name = "14") val x14: String,
    @Json(name = "15") val x15: String,
    @Json(name = "16") val x16: String,
    @Json(name = "17") val x17: String,
    @Json(name = "18") val x18: String,
    @Json(name = "19") val x19: String,
    @Json(name = "20") val x20: String
)

data class PosterImage(
    @Json(name = "tiny") val tiny: String,
    @Json(name = "small") val small: String,
    @Json(name = "medium") val medium: String,
    @Json(name = "large") val large: String,
    @Json(name = "original") val original: String,
    @Json(name = "meta") val meta: Meta
)

data class Meta(
    @Json(name = "dimensions") val dimensions: Dimensions
)

data class Dimensions(
    @Json(name = "tiny") val tiny: Tiny,
    @Json(name = "small") val small: Small,
    @Json(name = "medium") val medium: Medium,
    @Json(name = "large") val large: Large
)

data class Small(
    @Json(name = "width") val width: Any,
    @Json(name = "height") val height: Any
)

data class Large(
    @Json(name = "width") val width: Any,
    @Json(name = "height") val height: Any
)

data class Tiny(
    @Json(name = "width") val width: Any,
    @Json(name = "height") val height: Any
)

data class Medium(
    @Json(name = "width") val width: Any,
    @Json(name = "height") val height: Any
)

data class Relationships(
    @Json(name = "genres") val genres: Genres,
    @Json(name = "categories") val categories: Categories,
    @Json(name = "castings") val castings: Castings,
    @Json(name = "installments") val installments: Installments,
    @Json(name = "mappings") val mappings: Mappings,
    @Json(name = "reviews") val reviews: Reviews,
    @Json(name = "mediaRelationships") val mediaRelationships: MediaRelationships,
    @Json(name = "episodes") val episodes: Episodes,
    @Json(name = "streamingLinks") val streamingLinks: StreamingLinks,
    @Json(name = "animeProductions") val animeProductions: AnimeProductions,
    @Json(name = "animeCharacters") val animeCharacters: AnimeCharacters,
    @Json(name = "animeStaff") val animeStaff: AnimeStaff
)

data class Categories(
    @Json(name = "links") val links: Links
)


data class AnimeStaff(
    @Json(name = "links") val links: Links
)


data class StreamingLinks(
    @Json(name = "links") val links: Links
)


data class Castings(
    @Json(name = "links") val links: Links
)

data class Links(
    @Json(name = "self") val self: String,
    @Json(name = "related") val related: String
)

data class Episodes(
    @Json(name = "links") val links: Links
)


data class Genres(
    @Json(name = "links") val links: Links
)


data class Installments(
    @Json(name = "links") val links: Links
)


data class AnimeCharacters(
    @Json(name = "links") val links: Links
)


data class Mappings(
    @Json(name = "links") val links: Links
)


data class Reviews(
    @Json(name = "links") val links: Links
)


data class MediaRelationships(
    @Json(name = "links") val links: Links
)


data class AnimeProductions(
    @Json(name = "links") val links: Links
)

