package com.wasseemb.mal.model

import android.support.v7.util.DiffUtil
import com.wasseemb.mal.vo.Data.DataItem

class DataItemCallBack : DiffUtil.ItemCallback<DataItem>() {
  override fun areContentsTheSame(oldItem: DataItem?, newItem: DataItem?): Boolean {
    return oldItem?.id == newItem?.id
  }

  override fun areItemsTheSame(oldItem: DataItem?, newItem: DataItem?): Boolean {
    return oldItem == newItem
  }

}