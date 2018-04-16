package com.wasseemb.mal.ui

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import com.wasseemb.mal.AnimeListActivity
import com.wasseemb.mal.Extensions.loadUrl
import com.wasseemb.mal.R.layout
import com.wasseemb.mal.model.DisplayableItem
import com.wasseemb.mal.vo.Data.DataItem
import kotlinx.android.synthetic.main.anime_grid_content.view.imageView
import kotlinx.android.synthetic.main.anime_grid_content.view.textView


/**
 * Created by Wasseem on 18/03/2018.
 */
class AnimeAdapterDelegate(private val mParentActivity: AnimeListActivity,
    var onClickListener: View.OnClickListener,
    private val mTwoPane: Boolean) : AdapterDelegate<List<DisplayableItem>>() {
//  init {
//    mOnClickListener = View.OnClickListener { v ->
//      val item = v.tag as DataItem
//      if (mTwoPane) {
//        val fragment = AnimeDetailFragment().apply {
//          arguments = Bundle().apply {
//            putString(AnimeDetailFragment.ARG_ITEM_ID, item.attributes?.titles?.en)
//          }
//        }
//        mParentActivity.supportFragmentManager
//            .beginTransaction()
//            .replace(id.anime_detail_container, fragment)
//            .commit()
//      } else {
//        val intent = Intent(v.context, AnimeDetailActivity::class.java).apply {
//          putExtra(AnimeDetailFragment.ARG_ITEM_ID, item.id)
//        }
//        v.context.startActivity(intent)
//      }
//    }
//  }

  override fun onBindViewHolder(items: List<DisplayableItem>, position: Int, holder: ViewHolder,
      payloads: MutableList<Any>) {
    holder as GridViewHolder
    holder.bindTo(items[position])


  }

  override fun onCreateViewHolder(parent: ViewGroup): GridViewHolder {
    val viewHolder = GridViewHolder(LayoutInflater.from(parent.context)
        .inflate(layout.anime_grid_content, parent, false), onClickListener)
//    viewHolder.itemView.setOnClickListener {
//      val position = viewHolder.adapterPosition
//      if (position != RecyclerView.NO_POSITION) {
//        with(viewHolder.itemView) {
//          tag = items[position]
//          setOnClickListener(mOnClickListener)
//        }
//        // itemClickListener.onItemClick(itemList[position])
//      }
//    }
    return viewHolder
  }


  override fun isForViewType(items: List<DisplayableItem>, position: Int): Boolean {
    return items[position] is DataItem

  }


  inner class GridViewHolder(mView: View,
      onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(mView) {
    private val imageView: ImageView = mView.imageView
    private val textView: TextView = mView.textView


    init {
      mView.setOnClickListener { onClickListener }
    }

    fun bindTo(item: DisplayableItem) {
      item as DataItem
      imageView.loadUrl(item.attributes?.posterImage?.large)
      val titleList = item.attributes?.titles
      val titleText = titleList?.en ?: titleList?.enJp ?: titleList?.enUs
      textView.text = titleText
    }

  }

}
