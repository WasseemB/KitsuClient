package com.wasseemb.mal.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.Transformations.map
import android.arch.lifecycle.Transformations.switchMap
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.wasseemb.mal.repository.MalRepository
import com.wasseemb.mal.vo.Data.DataItem
import com.wasseemb.mal.vo.Data.KitsuResponse
import com.wasseemb.mal.vo.Response.AnimeResponse
import com.wasseemb.mal.vo.Response.Data

/**
 * Created by Wasseem on 14/03/2018.
 */
class MalViewModel : ViewModel() {
  private val malRepository = MalRepository()
  //  val data = MutableLiveData<String>()
  var animeId = MutableLiveData<String>()
  var animeName = MutableLiveData<String>()
  var animeUrlNextPage = MutableLiveData<String>()

  private val repoResult = map(animeName, {
    malRepository.animeSearch(it)
  })
  val posts = switchMap(repoResult, { it })!!


  fun animeSearch(): LiveData<PagedList<DataItem>> {
    return Transformations.switchMap(animeName) {
      malRepository.animeSearch(it)
    }
  }

  //No Longer Needed in favor of PagedList
//  fun animeSearch(): LiveData<KitsuResponse> {
//    return Transformations.switchMap(animeName) { search ->
//      malRepository.searchAnime(search)
//    }
//  }
//
//  fun animeSearchNextPage(): LiveData<KitsuResponse> {
//    return Transformations.switchMap(animeUrlNextPage) { animeUrlNextPage ->
//      malRepository.searchAnimeNextPage(animeUrlNextPage)
//    }
//  }

  fun getAnime(): LiveData<AnimeResponse> {
    return Transformations.switchMap(animeId) { animeId ->
      malRepository.getAnimeById(animeId)
    }
  }

  fun getAnimeGenres(): LiveData<String> {
    return Transformations.switchMap(animeId) { animeId ->
      malRepository.getAnimeGenres(animeId)
    }
  }
}