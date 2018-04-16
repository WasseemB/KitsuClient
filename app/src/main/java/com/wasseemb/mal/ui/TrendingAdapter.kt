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
import com.wasseemb.mal.R.layout
import com.wasseemb.mal.ui.TrendingAdapter.GridViewHolder
import com.wasseemb.mal.vo.Data.DataItem
import kotlinx.android.synthetic.main.anime_grid_content.view.imageView
import kotlinx.android.synthetic.main.anime_grid_content.view.textView
import kotlin.properties.Delegates


/**
 * Created by Wasseem on 18/03/2018.
 */
class TrendingAdapter(private val mParentActivity: AnimeListActivity,
    private val mTwoPane: Boolean) : RecyclerView.Adapter<GridViewHolder>() {

  lateinit var itemClickListener: ItemClickListener

  interface ItemClickListener {
    fun onItemClick(item: DataItem)
  }

  var itemList: List<DataItem> by Delegates.observable(
      emptyList()) { _, old, new ->
    autoNotify(old, new) { o, n -> o == n }
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
    private val imageView: ImageView = mView.imageView
    private val textView: TextView = mView.textView

    fun bindTo(item: DataItem) {
      imageView.loadUrl(item.attributes?.posterImage?.large)
      with(item.attributes?.titles!!)
      {
        textView.text = en ?: enJp ?: enUs
      }
    }
  }
}