package com.dsckiet.youthhub

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.Adapters.PlaylistItemAdapter
import com.dsckiet.youthhub.ViewModel.PlaylistViewModel
import com.dsckiet.youthhub.ViewModel.PlaylistViewModelFactory
import com.dsckiet.youthhub.databinding.FragmentPlaylistVideoListBinding
import com.dsckiet.youthhub.model.Item
import com.example.youthhub.Repo.Repository


class PlaylistVideoListFragment : Fragment() {

    private lateinit var  binding: FragmentPlaylistVideoListBinding
    private lateinit var  PlaylistID:String
    private lateinit var playlistViewModel:PlaylistViewModel
    private lateinit var playlistItemAdapter:PlaylistItemAdapter
    lateinit var manager: LinearLayoutManager
    var pageToken = ""
    var isScrolling:Boolean = false
    var scrolledoutitems : Int = 0
    var itemsy : List<Item> = ArrayList()
    var currentitems:Int = 0
    var totalitems: Int =0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_playlist_video_list,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        manager = LinearLayoutManager(requireContext())
        PlaylistID = bundle!!.getString("PlaylistVideoList_PlaylistID").toString()
        binding.playlistvideolistBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_playlistVideoListFragment_to_homeFragment)
        }

        getplaylistitem()
        binding.playlistvideolistRecview.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                    binding.playlistvideolistInfo.visibility = View.GONE
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentitems = recyclerView.layoutManager!!.childCount
                totalitems = recyclerView.layoutManager!!.itemCount
                scrolledoutitems = (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

                if(isScrolling && (currentitems + scrolledoutitems == totalitems)){
                    isScrolling = false
                    binding.playlistvideolistProgressbar.visibility = View.VISIBLE
                    getplaylistitem()
//                    fetchdata()
                }
            }
        })

    }

    private fun getplaylistitem() {
        val repo = Repository(requireContext())
        val vmf= PlaylistViewModelFactory(repo)
        playlistViewModel = ViewModelProvider(this,vmf).get(PlaylistViewModel::class.java)
        playlistItemAdapter = PlaylistItemAdapter(requireContext(),itemsy)
        binding.playlistvideolistRecview.adapter = playlistItemAdapter
        Log.e("ID is : ",PlaylistID)
        if(pageToken == null){
            pageToken = ""
        }
        playlistViewModel.getPlaylistItem("snippet",PlaylistID,pageToken)
        Log.e("PlaylistOPFRAGMENTID" , PlaylistID)
        playlistViewModel.getPlaylist("snippet",PlaylistID)

        playlistViewModel.playlist.observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful){
                binding.playlistvideolistPlaytitle.text = it.body()?.items?.get(0)?.snippet?.title
            }
        })

        playlistViewModel.playlistitem.observe(viewLifecycleOwner, Observer {

            if (it.isSuccessful){
                itemsy = itemsy + (it.body()?.items!!)
                playlistItemAdapter.playlistitemsetStateWiseTracker(itemsy)
                pageToken = it.body()?.nextPageToken.toString()
            }
            binding.playlistvideolistProgressbar.visibility = View.GONE
            Log.d("BOLT","success"+it.toString())

        })
        binding.playlistvideolistRecview.layoutManager = manager
        binding.playlistvideolistRecview.adapter = playlistItemAdapter


    }

}