package com.wasseemb.mal.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.wasseemb.mal.Extensions.loadUrl
import com.wasseemb.mal.Extensions.toggleLines
import com.wasseemb.mal.R
import com.wasseemb.mal.R.layout
import com.wasseemb.mal.vo.Response.AnimeResponse
import kotlinx.android.synthetic.main.activity_anime_detail.detail_toolbar

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
  private var animeId: String? = null
  private lateinit var animePosterImageView: ImageView
  private lateinit var animeTitleTextView: TextView
  private lateinit var animeSynopsisTextView: TextView
  private lateinit var animeGenresTextView: TextView
  private lateinit var readMoreButton: Button


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      if (it.containsKey(ARG_ANIME_ID)) {
        // Load the dummy content specified by the fragment
        // arguments. In a real-world scenario, use a Loader
        // to load content from a content provider.
        animeId = it.getString(ARG_ANIME_ID)
        // viewModel.id.value = mItem


        //activity?.toolbar_layout?.title = mItem
      }
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(layout.anime_detail, container, false)

    animePosterImageView = rootView.findViewById(R.id.media_image)
    animeTitleTextView = rootView.findViewById(R.id.primary_text)
    animeGenresTextView = rootView.findViewById(R.id.sub_text)
    animeSynopsisTextView = rootView.findViewById(R.id.supporting_text)
    readMoreButton = rootView.findViewById(R.id.readMore)

    val viewModel = ViewModelProviders.of(this).get(MalViewModel::class.java)
    observeViewModel(viewModel)
    Log.d("AnimeDetailFragment", animeId + "")
    viewModel.animeId.value = animeId

    return rootView
  }

  private fun observeViewModel(viewModel: MalViewModel) {
    // Update the list when the data changes
    viewModel.getAnime().observe(this, Observer<AnimeResponse> { malResponse ->
      if (malResponse != null) {
        val titleText = getTitle(malResponse)
        animeTitleTextView.text = titleText
        activity?.detail_toolbar?.title = titleText
        animeSynopsisTextView.text = malResponse.data.attributes.synopsis
        handleReadMore()
        animePosterImageView.loadUrl(malResponse.data.attributes.posterImage.large)
      }
    })

    viewModel.getAnimeGenres().observe(this, Observer<String> {
      if (it != null) animeGenresTextView.text = it
    })
  }

  companion object {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    const val ARG_ANIME_ID = "anime_id"
  }


  private fun getTitle(malResponse: AnimeResponse): String? {
    val titleList = malResponse.data.attributes.titles
    return when {
      !titleList.en.isNullOrBlank() -> titleList.en
      !titleList.enUs.isNullOrBlank() -> titleList.enUs
      !titleList.enJp.isNullOrBlank() -> titleList.enJp
      else -> titleList.jaJp
    }
  }

  private fun handleReadMore() {
    if (animeSynopsisTextView.lineCount < 3)
      readMoreButton.visibility = View.GONE
    readMoreButton.setOnClickListener {
      if (animeSynopsisTextView.toggleLines()) readMoreButton.text = "Read More"
      else readMoreButton.text = "Close"
    }
  }

}
