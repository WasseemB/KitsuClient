package com.wasseemb.mal.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import com.wasseemb.mal.AnimeListActivity
import com.wasseemb.mal.model.DisplayableItem


/**
 * Created by Wasseem on 31/07/2017.
 */
class AnimeAdapter(activity: AnimeListActivity,
    var items: List<DisplayableItem>,
    onClickListener: View.OnClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  private val delegatesManager = AdapterDelegatesManager<List<DisplayableItem>>()
  private val adapterDelegate: AnimeAdapterDelegate = AnimeAdapterDelegate(activity,
      onClickListener, false)

  init {
    delegatesManager.addDelegate(
        adapterDelegate)

//    delegatesManager.addDelegate(
//        LoadingDelegateAdapter())
  }

  override fun getItemViewType(position: Int): Int {
    return delegatesManager.getItemViewType(items, position)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return delegatesManager.onCreateViewHolder(parent, viewType)
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    delegatesManager.onBindViewHolder(items, position, holder)
  }


  override fun getItemCount(): Int {
    return items.size
  }


}