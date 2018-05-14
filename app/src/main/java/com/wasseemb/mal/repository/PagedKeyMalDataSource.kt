package com.wasseemb.mal.repository

import android.arch.paging.PageKeyedDataSource
import com.wasseemb.mal.api.MalApiService
import com.wasseemb.mal.vo.Data.DataItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class PagedKeyMalDataSource(private val malApiService: MalApiService,
    private val searchTerm: String) : PageKeyedDataSource<String, DataItem>() {
  override fun loadInitial(params: LoadInitialParams<String>,
      callback: LoadInitialCallback<String, DataItem>) {

    malApiService.searchAnime(searchTerm).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe {
      val data = it
      callback.onResult(data.data as List<DataItem>, data.links?.first, data.links?.next)
    }


    // callback.onResult(data.data as List<DataItem>, data.links?.first, data.links?.next)

  }

  override fun loadAfter(params: LoadParams<String>,
      callback: LoadCallback<String, DataItem>) {
    malApiService.searchAnimeNextPage(params.key).subscribeOn(Schedulers.io()).observeOn(
        AndroidSchedulers.mainThread()).subscribe {
      val data = it
      callback.onResult(data.data as List<DataItem>, data.links?.next)
    }

  }

  override fun loadBefore(params: LoadParams<String>,
      callback: LoadCallback<String, DataItem>) {
  }
}