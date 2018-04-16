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
  private val newsRepository = MalRepository()
  val data = MutableLiveData<String>()
  var animeId = MutableLiveData<String>()
  var search = MutableLiveData<String>()

  fun animeSearch(): LiveData<KitsuResponse> {
    return Transformations.switchMap(search) { search ->
      newsRepository.searchAnime(search)
    }
  }

  fun getAnime(): LiveData<AnimeResponse> {
    return Transformations.switchMap(animeId) { animeId ->
      newsRepository.getAnime(animeId)
    }
  }

  fun getAnimeGenres(): LiveData<String> {
    return Transformations.switchMap(animeId) { animeId ->
      newsRepository.getAnimeGenres(animeId)
    }
  }
}