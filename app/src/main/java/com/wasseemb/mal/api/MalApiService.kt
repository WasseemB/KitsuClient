package com.wasseemb.mal.api

import com.wasseemb.mal.vo.Data.KitsuResponse
import com.wasseemb.mal.vo.Response.AnimeGenresResponse
import com.wasseemb.mal.vo.Response.AnimeResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Wasseem on 14/03/2018.
 */
interface MalApiService {
  @GET("edge/anime")
  fun getTrendingAnime()
      : Call<KitsuResponse>

  @GET("edge/anime")
  fun searchAnime(@Query("filter[text]") filter: String): Observable<KitsuResponse>

  @GET
  fun searchAnimeNextPage(@Url url: String): Observable<KitsuResponse>

  @GET("edge/anime/{id}")
  fun getAnimeByID(@Path("id") id: String): Observable<AnimeResponse>

  @GET("edge/anime/{id}/genres")
  fun getAnimeGenre(@Path("id") id: String): Observable<AnimeGenresResponse>


  companion object {
    private const val API_URL = "https://kitsu.io/api/"
    fun create(): MalApiService {
      val logging = HttpLoggingInterceptor()
      logging.level = HttpLoggingInterceptor.Level.BODY
      val httpClient = OkHttpClient.Builder()
      httpClient.addInterceptor(logging)
      val retrofit = Retrofit.Builder()
          .addConverterFactory(
              MoshiConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

          .baseUrl(API_URL)

          // .client(httpClient.build())
          .build()
      return retrofit.create(MalApiService::class.java)
    }
  }

}