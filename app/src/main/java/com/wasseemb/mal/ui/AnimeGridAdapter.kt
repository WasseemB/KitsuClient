package com.wasseemb.mal.ui

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.wasseemb.mal.AnimeListActivity
import com.wasseemb.mal.Extensions.inflate
import com.wasseemb.mal.Extensions.loadUrl
import com.wasseemb.mal.R
import com.wasseemb.mal.R.layout
import com.wasseemb.mal.model.DataItemCallBack
import com.wasseemb.mal.ui.AnimeGridAdapter.GridViewHolder
import com.wasseemb.mal.vo.Data.DataItem


/**
 * Created by Wasseem on 18/03/2018.
 */
class AnimeGridAdapter : PagedListAdapter<DataItem, GridViewHolder>(
    DataItemCallBack()) {

  lateinit var itemClickListener: ItemClickListener

  interface ItemClickListener {
    fun onItemClick(item: DataItem)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
    val viewHolder = GridViewHolder(parent.inflate(layout.anime_grid_content))
    viewHolder.itemView.setOnClickListener {
      val position = viewHolder.adapterPosition
      if (position != RecyclerView.NO_POSITION)
        //getItem given by implementing ListAdapter(Support Library)
        itemClickListener.onItemClick(getItem(position)!!)
    }
    return viewHolder
  }

  override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
    holder.bindTo(getItem(position)!!)
  }

  inner class GridViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
    private val animePosterImageView: ImageView = mView.findViewById(R.id.imgAnimePoster)
    private val animeTitleTextView: TextView = mView.findViewById(R.id.tvAnimeTitle)

    fun bindTo(item: DataItem) {
      animePosterImageView.loadUrl(item.attributes?.posterImage?.large)
      with(item.attributes?.titles!!)
      {
        animeTitleTextView.text =
            when {
              !en.isNullOrBlank() -> en
              !enUs.isNullOrBlank() -> enUs
              !enJp.isNullOrBlank() -> enJp
              else -> jaJp
            }
      }
    }
  }
}