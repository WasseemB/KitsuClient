package com.wasseemb.mal.ui

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.wasseemb.mal.AnimeListActivity
import com.wasseemb.mal.Extensions.autoNotify
import com.wasseemb.mal.Extensions.inflate
import com.wasseemb.mal.Extensions.loadUrl
import com.wasseemb.mal.R
import com.wasseemb.mal.R.layout
import com.wasseemb.mal.ui.AnimeGridAdapter.GridViewHolder
import com.wasseemb.mal.vo.Data.DataItem
import kotlin.properties.Delegates


/**
 * Created by Wasseem on 18/03/2018.
 */
class AnimeGridAdapter(private val mParentActivity: AnimeListActivity,
    private val mTwoPane: Boolean) : RecyclerView.Adapter<GridViewHolder>() {

  lateinit var itemClickListener: ItemClickListener

  interface ItemClickListener {
    fun onItemClick(item: DataItem)
  }


  var itemList: List<DataItem> by Delegates.observable(
      emptyList()) { _, old, new ->
    autoNotify(old, new) { o, n -> o.id == n.id }
  }

  fun addToItemList(newItemList: List<DataItem>) {
    itemList += newItemList
    itemList
  }


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
    val viewHolder = GridViewHolder(parent.inflate(layout.anime_grid_content))
    viewHolder.itemView.setOnClickListener {
      val position = viewHolder.adapterPosition
      if (position != RecyclerView.NO_POSITION)
        itemClickListener.onItemClick(itemList[position])
    }
    return viewHolder
  }

  override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
    holder.bindTo(itemList[position])
  }


  override fun getItemCount(): Int {
    return itemList.size
  }


  inner class GridViewHolder(mView: View) : ViewHolder(mView) {
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
//            en ?: enJp ?: enUs ?: jaJp
      }
    }
  }
}