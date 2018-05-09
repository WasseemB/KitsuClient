package com.wasseemb.mal.Extensions

import android.animation.ObjectAnimator
import android.support.v7.util.DiffUtil
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * Created by Wasseem on 23/03/2018.
 */
var toggleLinesBoolean: Boolean = true
var previousTotal = 0 // The total number of items in the dataset after the last load
var loading = true // True if we are still waiting for the last set of data to load.
var visibleThreshold = 6 // The minimum amount of items to have below your current scroll position before loading more.
var firstVisibleItem: Int = 0
var visibleItemCount: Int = 0
var totalItemCount: Int = 0
var current_page = 1

fun TextView.toggleLines(): Boolean {
  if (toggleLinesBoolean) {
    val height = this.measuredHeight
    this.height = height
    this.maxLines = Integer.MAX_VALUE //expand fully
    this.measure(View.MeasureSpec.makeMeasureSpec(this.measuredWidth, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
    val newHeight = this.measuredHeight
    val animation = ObjectAnimator.ofInt(this, "height", height, newHeight)
    animation.setDuration(250).start()
    toggleLinesBoolean = !toggleLinesBoolean
    return toggleLinesBoolean
  } else {
    val height = this.measuredHeight
    this.height = height
    this.maxLines = 3
    this.setLines(3)//expand fully
    this.measure(View.MeasureSpec.makeMeasureSpec(this.measuredWidth, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
    val newHeight = this.measuredHeight
    val animation = ObjectAnimator.ofInt(this, "height", height, newHeight)
    animation.setDuration(250).start()
    toggleLinesBoolean = !toggleLinesBoolean
    return toggleLinesBoolean
  }
}

fun ImageView.loadUrl(url: String?) {
  Picasso.with(context).load(url).into(this)

}

fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<T>, newList: List<T>,
    compare: (T, T) -> Boolean) {
  val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return compare(oldList[oldItemPosition], newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
  })

  diff.dispatchUpdatesTo(this)
}


inline fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun RecyclerView.onScrollToEnd(linearLayoutManager: GridLayoutManager,
    onScrollNearEnd: (Unit) -> Unit) = addOnScrollListener(object : RecyclerView.OnScrollListener() {
  override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
    visibleItemCount = recyclerView?.childCount!!
    totalItemCount = linearLayoutManager.itemCount
    firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()
    if (loading) {
      if (totalItemCount > previousTotal) {
        loading = false
        previousTotal = totalItemCount;
      }
    }
    if (!loading && (totalItemCount - visibleItemCount)
        <= (firstVisibleItem + visibleThreshold)) {
      current_page++
      onScrollNearEnd(Unit)
      loading = true
    }
  }
})