package com.wasseemb.mal

//import kotlinx.android.synthetic.main.activity_anime_list.toolbar
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.wasseemb.mal.ui.AnimeDetailFragment
import com.wasseemb.mal.ui.AnimeGridAdapter
import com.wasseemb.mal.ui.AnimeGridAdapter.ItemClickListener
import com.wasseemb.mal.ui.MalViewModel
import com.wasseemb.mal.vo.Data.DataItem
import kotlinx.android.synthetic.main.activity_anime_list.fab
import kotlinx.android.synthetic.main.activity_anime_list.toolbar
import kotlinx.android.synthetic.main.anime_list.anime_detail_container


/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [AnimeDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class AnimeListActivity : AppCompatActivity(), ItemClickListener {
  private var mTwoPane: Boolean = false
  lateinit var recyclerView: RecyclerView
  lateinit var animeGridAdapter: AnimeGridAdapter
  lateinit var data: List<DataItem>
  lateinit var viewModel: MalViewModel
  var nextPage: String? = ""

  override fun onItemClick(item: DataItem) {
    val intent = Intent(this, AnimeDetailActivity::class.java).apply {
      putExtra(AnimeDetailFragment.ARG_ANIME_ID, item.id)
    }
    startActivity(intent)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_anime_list)
    setSupportActionBar(toolbar)
    toolbar.title = title
    if (anime_detail_container != null) {
      mTwoPane = true
    }

    setupRecyclerView()
    viewModel = ViewModelProviders.of(this).get(MalViewModel::class.java)
    observeViewModel(viewModel)
    fab.setOnClickListener {
      viewModel.animeName.value = "Attack on titan"

    }
    //Replaced with PagedList
//    recyclerView.onScrollToEnd(recyclerView.layoutManager as GridLayoutManager,
//        { viewModel.animeUrlNextPage.value = nextPage })
  }

  private fun setupRecyclerView() {
    recyclerView = findViewById(R.id.anime_recycler)
    val numberOfColumns = 3
    recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
    animeGridAdapter = AnimeGridAdapter()
    animeGridAdapter.itemClickListener = this
    recyclerView.adapter = animeGridAdapter
  }

  private fun observeViewModel(viewModel: MalViewModel) {
    viewModel.animeSearch().observe(this, Observer<PagedList<DataItem>> {
      animeGridAdapter.submitList(it)
    })
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
    R.id.action_search -> {
      (item.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(newText: String?): Boolean {
          return false
        }

        override fun onQueryTextSubmit(query: String?): Boolean {
          viewModel.animeName.value = query
          return false
        }
      })
      true
    }
    else -> super.onOptionsItemSelected(item)
  }

  //Replaced with PagedList
  // Update the list when the data changes
//    viewModel.animeSearch().observe(this, Observer<KitsuResponse> { malResponse ->
//      if (malResponse != null) {
//        Log.d("Data", malResponse.data?.size.toString())
//        val animeList = malResponse.data as List<DataItem>
//        data = animeList
//        animeGridAdapter.submitList(animeList)
//        //recyclerView.adapter.notifyDataSetChanged()
//        nextPage = malResponse.links?.next
//        Log.d("observeViewModel", "firstModel")
//        //recyclerView.adapter.notifyDataSetChanged()
//      }
//    })
//
//
//
//    viewModel.animeSearchNextPage().observe(this, Observer<KitsuResponse> { malResponse ->
//      if (malResponse != null) {
//        Log.d("Data", malResponse.data?.size.toString())
//        val animeList = malResponse.data as List<DataItem>
//        data += animeList
//        animeGridAdapter.submitList(data)
//        //((recyclerView.adapter) as AnimeGridAdapter).addToItemList(animeList)
//        nextPage = malResponse.links?.next
//        Log.d("observeViewModel", "secondModel")
//
//        //recyclerView.adapter.notifyDataSetChanged()
//      }
//    })


}
