package com.wasseemb.mal.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.wasseemb.mal.api.MalApiService
import com.wasseemb.mal.vo.Data.DataItem
import com.wasseemb.mal.vo.Data.KitsuResponse

class MalDataSourceFactory(private val malApiService: MalApiService,
    private val searchTerm: String) : DataSource.Factory<String, DataItem>() {
  val sourceLiveData = MutableLiveData<PagedKeyMalDataSource>()

  override fun create(): DataSource<String, DataItem> {
    val source = PagedKeyMalDataSource(malApiService, searchTerm)
    sourceLiveData.postValue(source)
    return source
  }
}