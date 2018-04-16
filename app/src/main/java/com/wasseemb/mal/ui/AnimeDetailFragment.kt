package com.wasseemb.mal.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.wasseemb.mal.Extensions.loadUrl
import com.wasseemb.mal.Extensions.toggleLines
import com.wasseemb.mal.R
import com.wasseemb.mal.R.layout
import com.wasseemb.mal.vo.Response.AnimeGenresResponse
import com.wasseemb.mal.vo.Response.AnimeResponse
import kotlinx.android.synthetic.main.activity_anime_detail.detail_toolbar
import kotlinx.android.synthetic.main.anime_detail_test_3.readMore


/**
 * A fragment representing a single Anime detail screen.
 * This fragment is either contained in a [AnimeListActivity]
 * in two-pane mode (on tablets) or a [AnimeDetailActivity]
 * on handsets.
 */
class AnimeDetailFragment : Fragment() {

  /**
   * The dummy content this fragment is presenting.
   */
  private var mItem: String? = null
  private lateinit var imageView: ImageView
  private lateinit var title: TextView
  private lateinit var synopsis: TextView
  private lateinit var genre: TextView


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      if (it.containsKey(ARG_ITEM_ID)) {
        // Load the dummy content specified by the fragment
        // arguments. In a real-world scenario, use a Loader
        // to load content from a content provider.
        mItem = it.getString(ARG_ITEM_ID)
        // viewModel.id.value = mItem


        //activity?.toolbar_layout?.title = mItem
      }
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(layout.anime_detail_test, container, false)

    imageView = rootView.findViewById(R.id.media_image)
    title = rootView.findViewById(R.id.primary_text)
    genre = rootView.findViewById(R.id.sub_text)
    synopsis = rootView.findViewById(R.id.supporting_text)

    val viewModel = ViewModelProviders.of(this).get(MalViewModel::class.java)
    observeViewModel(viewModel)
    Log.d("AnimeDetailFragment", mItem + "")
    viewModel.animeId.value = mItem

    return rootView
  }

  private fun observeViewModel(viewModel: MalViewModel) {
    // Update the list when the data changes
    viewModel.getAnime().observe(this, Observer<AnimeResponse> { malResponse ->
      if (malResponse != null) {
        val titleText = getTitle(malResponse)
        title.text = titleText
        activity?.detail_toolbar?.title = titleText
        synopsis.text = malResponse.data.attributes.synopsis
        handleReadMore()
        imageView.loadUrl(malResponse.data.attributes.posterImage.large)
      }
    })

    viewModel.getAnimeGenres().observe(this, Observer<String> {
      if (it != null) {
        genre.text = it
      }
    })
  }

  companion object {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    const val ARG_ITEM_ID = "item_id"
  }


  private fun getTitle(malResponse: AnimeResponse): String? {
    val titleList = malResponse.data.attributes.titles
    return titleList.en ?: titleList.enJp ?: titleList.enUs
  }

  private fun handleReadMore() {
    if (synopsis.lineCount < 3)
      readMore.visibility = View.GONE
    readMore.setOnClickListener {
      if (synopsis.toggleLines())
        readMore.text = "Read More"
      else
        readMore.text = "Close"
    }
  }

}
