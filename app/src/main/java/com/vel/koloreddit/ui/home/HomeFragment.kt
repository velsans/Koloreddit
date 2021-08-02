package com.vel.koloreddit.ui.home

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.vel.koloreddit.ExtractXML
import com.vel.koloreddit.R
import com.vel.koloreddit.room.DatabaseClient
import com.vel.koloreddit.ui.home.model.Entry
import com.vel.koloreddit.ui.home.model.FeedResponce
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), FeedFilterBottomFragment.OnInputSelected {
    val dialog = FeedFilterBottomFragment()
    companion object {
        private lateinit var homeViewModel: HomeViewModel

        //private lateinit var feedViewModel: FeedViewModel
        private lateinit var homeViewModelFactory: HomeViewModelFactory
        private const val TAG = "HomeFragment"
        var feeddata: List<FeedResponce> = java.util.ArrayList<FeedResponce>()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val View = inflater.inflate(R.layout.fragment_home, container, false)
        return View
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val homeRepository = HomeRepository()
        homeViewModelFactory = HomeViewModelFactory(homeRepository)
        homeViewModel = ViewModelProvider(
            this,
            homeViewModelFactory
        ).get(HomeViewModel::class.java)
        progressBar.visibility = View.VISIBLE
        homeViewModel.pushPopular("hot")
        homeViewModel.home.observe(viewLifecycleOwner, {
            //Log.d(TAG, "onResponse: feed: " + it.body().toString())
            //Log.d(TAG, "onResponse: Server Response: $it")

            val entrys: List<Entry> = it.body()?.entry as List<Entry>
            for (i in entrys.indices) {
                val extractXML1 = entrys[i].content?.let { it1 -> ExtractXML(it1, "<a href=") }
                val feedContent: List<String?> = extractXML1!!.start()
                val extractXML2 = entrys[i].content?.let { it1 -> ExtractXML(it1, "<img src=") }
                var feedContents: String? = null
                try {
                    if (extractXML2 != null) {
                        val splitXML = extractXML2.start()[0].split("?")
                        feedContents = splitXML[0]
                    }
                } catch (e: NullPointerException) {
                    feedContent.toMutableList().add(null)
                    Log.e(TAG, "onResponse: NullPointerException(thumbnail):" + e.message)
                } catch (e: IndexOutOfBoundsException) {
                    feedContent.toMutableList().add(null)
                    Log.e(TAG, "onResponse: IndexOutOfBoundsException(thumbnail):" + e.message)
                }
                val feeds = entrys[i].id?.let { it1 ->
                    FeedResponce(
                        it1,
                        entrys[i].title,
                        entrys[i].authors?.name,
                        entrys[i].updated,
                        feedContent[0],
                        //"https://www.learningcontainer.com/wp-content/uploads/2020/08/Large-Sample-png-Image-download-for-Testing.png"
                        //"https://kinvid0.bluestone.com/output/mp4/BIPM0384R05-VIDEO-20556.mp4/BIPM0384R05-VIDEO-20556.mp4"
                        feedContents
                    )
                }
                AsyncTask.execute {
                    context?.let {
                        if (feeds != null) {
                            DatabaseClient.getInstance(requireActivity())?.appDatabase
                                ?.feedDao()
                                ?.addFeed(feeds)
                        }
                    }
                }
            }
            Toast.makeText(context, "Sucessfully Inserted", Toast.LENGTH_SHORT).show()
            // Feeds
            getAllrecords()
            progressBar.visibility = View.GONE
            swipeContainer.isRefreshing = false
        })

        swipeContainer.setOnRefreshListener {
            homeViewModel.pushPopular("hot")
        }
        swipeContainer.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
        feedfilter.setOnClickListener {
            dialog.setTargetFragment(this, 1)
            fragmentManager?.let {
                // dialog.dismiss()
                dialog.show(it, "MyFeedFilterDialog")
            }
        }
        feedfilter.text = "HOT POSTS"

    }

    private fun getAllrecords() {
        AsyncTask.execute {
            feeddata = DatabaseClient.getInstance(requireActivity())?.appDatabase
                ?.feedDao()
                ?.readAllfeed()!!
        }
        if (feeddata.isNotEmpty()) {
            val moviesAdapter = FeedsAdapter(feeddata as ArrayList<FeedResponce>)
            val layoutManager = LinearLayoutManager(requireContext())
            feed_recyclerView.layoutManager = layoutManager
            feed_recyclerView.itemAnimator = DefaultItemAnimator()
            feed_recyclerView.adapter = moviesAdapter
        }
    }

    override fun sendInput(filterUrl: String, position: Int) {
        dialog.dismiss()
        feedfilter.text = filterUrl
        val filterUrls: String? = filterurls(position)
        homeViewModel.pushPopular(filterUrls)
    }

    private fun filterurls(number: Int): String {
        var result = ""
        when (number) {
            0 -> result = "hot"
            1 -> result = "new"
            2 -> result = "top"
            3 -> result = "raising"
        }
        return result
    }
}