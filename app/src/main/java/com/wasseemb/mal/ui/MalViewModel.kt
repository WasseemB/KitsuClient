package com.wasseemb.mal.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.wasseemb.mal.repository.MalRepository
import com.wasseemb.mal.vo.Data.KitsuResponse
import com.wasseemb.mal.vo.Response.AnimeResponse

/**
 * Created by Wasseem on 14/03/2018.
 */
class MalViewModel : ViewModel() {
  private val malRepository = MalRepository()
  //  val data = MutableLiveData<String>()
  var animeId = MutableLiveData<String>()
  var animeName = MutableLiveData<String>()
  var animeUrlNextPage = MutableLiveData<String>()


  fun animeSearch(): LiveData<KitsuResponse> {
    return Transformations.switchMap(animeName) { search ->
      malRepository.searchAnime(search)
    }
  }

  fun animeSearchNextPage(): LiveData<KitsuResponse> {
    return Transformations.switchMap(animeUrlNextPage) { animeUrlNextPage ->
      malRepository.searchAnimeNextPage(animeUrlNextPage)
    }
  }

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