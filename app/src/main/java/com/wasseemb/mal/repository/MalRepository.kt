package com.wasseemb.mal.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.wasseemb.mal.api.MalApiService
import com.wasseemb.mal.vo.Data.KitsuResponse
import com.wasseemb.mal.vo.Response.AnimeGenresResponse
import com.wasseemb.mal.vo.Response.AnimeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call

/**
 * Created by Wasseem on 14/03/2018.
 */

class MalRepository {
  private val malApiService = MalApiService.create()
  private val animeSearchResult = MutableLiveData<KitsuResponse>()
  private val animeResult = MutableLiveData<AnimeResponse>()
  private val animeSearchResultNextPage = MutableLiveData<KitsuResponse>()

  private val animeGenreResult = MutableLiveData<AnimeGenresResponse>()
  private val animeGenreResultAsString = MutableLiveData<String>()

//
//  fun getTopHeadlines(id: Int): LiveData<KitsuResponse> {
//    malApiService.getTrendingAnime().enqueue(object : retrofit2.Callback<KitsuResponse> {
//      override fun onFailure(call: Call<KitsuResponse>?, t: Throwable?) {
//        Log.d("Data", t.toString())
//      }
//
//      override fun onResponse(call: Call<KitsuResponse>?,
//          response: retrofit2.Response<KitsuResponse>?) {
//        animeSearchResult.value = response?.body()
//      }
//    })
//    return animeSearchResult
//  }


  fun searchAnime(search: String): LiveData<KitsuResponse> {
    malApiService.searchAnime(search).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe { animeSearchResult.value = it }
    return animeSearchResult
  }
  fun searchAnimeNextPage(url: String): LiveData<KitsuResponse> {
    malApiService.searchAnimeNextPage(url).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe { animeSearchResultNextPage.value = it }
    return animeSearchResultNextPage
  }



  fun getAnimeById(id: String): LiveData<AnimeResponse> {
    malApiService.getAnimeByID(id).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe { animeResult.value = it }
    return animeResult
  }


  fun getAnimeGenres(id: String): LiveData<String> {
    malApiService.getAnimeGenre(id).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .map {
          //join object names which are nested inside
          //it.data.map { it.attributes.name }.joinToString(separator = " ")
          it.data.joinToString(separator = " ") { it.attributes.name }
        }
        .subscribe { animeGenreResultAsString.value = it }
    return animeGenreResultAsString
  }


}