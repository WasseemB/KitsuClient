package com.wasseemb.mal.Extensions

import android.animation.ObjectAnimator
import android.support.v7.util.DiffUtil
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