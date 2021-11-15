package com.example.youthhub.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AbsListView.OnScrollListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.Adapters.PlaylistItemAdapter
import com.dsckiet.youthhub.Adapters.PlaylistItemAdapter.PlaylistItemViewHolder
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.ViewModel.PlaylistViewModel
import com.dsckiet.youthhub.ViewModel.PlaylistViewModelFactory
import com.dsckiet.youthhub.databinding.FragmentPlaylistOpenedBinding
import com.dsckiet.youthhub.model.Item
import com.example.youthhub.Repo.Repository


class PlaylistOpenedFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistOpenedBinding
    private lateinit var playlistViewModel: PlaylistViewModel
    private lateinit var playlistItemAdapter: PlaylistItemAdapter
    var isScrolling:Boolean = false
    var currentitems:Int = 0
    var totalitems: Int =0
    lateinit var recyclerView: RecyclerView
    var scrolledoutitems : Int = 0
    var pageToken = ""
    var id = ""
    lateinit var manager: LinearLayoutManager
    var itemsy : List<Item> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_playlist_opened,container,false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.playlistOpenedProgressbar.visibility = View.GONE
        manager = LinearLayoutManager(requireContext())
        recyclerView =  binding.playlistOpenedRecview
//        playlistItemAdapter = PlaylistItemAdapter(requireContext(),itemsy)
//        recyclerView.adapter = playlistItemAdapter
        binding.searchBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_playlistOpenedFragment_to_playlistFragment)
        }
        viewvideosofplaylist()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                    binding.playlistInfo.visibility = View.GONE
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentitems = recyclerView.layoutManager!!.childCount
                totalitems = recyclerView.layoutManager!!.itemCount
                scrolledoutitems = (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()

                if(isScrolling && (currentitems + scrolledoutitems == totalitems)){
                    isScrolling = false
                    binding.playlistOpenedProgressbar.visibility = View.VISIBLE
                    viewvideosofplaylist()
//                    fetchdata()
                }
            }
        })
    }

    private fun viewvideosofplaylist() {

        val repository= Repository(this.requireContext())
        val vmf= PlaylistViewModelFactory(repository)
        playlistViewModel = ViewModelProvider(this,vmf).get(PlaylistViewModel::class.java)
        playlistItemAdapter = PlaylistItemAdapter(requireContext(),itemsy)
        recyclerView.adapter = playlistItemAdapter
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
         id = sharedPref.getString("PlaylistId", "").toString()
        Log.e("ID is : ",id)
        if(pageToken == null){
            pageToken = ""
        }
        playlistViewModel.getPlaylistItem("snippet",id,pageToken)
        Log.e("PlaylistOPFRAGMENTID" , id)
        playlistViewModel.getPlaylist("snippet",id)

        playlistViewModel.playlist.observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful){
                binding.playOpenedPlaytitle.text = it.body()?.items?.get(0)?.snippet?.title
            }
        })

        playlistViewModel.playlistitem.observe(viewLifecycleOwner, Observer {

            if (it.isSuccessful){
                itemsy = itemsy + (it.body()?.items!!)
                playlistItemAdapter.playlistitemsetStateWiseTracker(itemsy)
                pageToken = it.body()?.nextPageToken.toString()
            }
            binding.playlistOpenedProgressbar.visibility = View.GONE
            Log.d("BOLT","success"+it.toString())

        })
        recyclerView.layoutManager = manager
        recyclerView.adapter = playlistItemAdapter


    }

    private fun fetchdata() {
        playlistViewModel.getPlaylistItem("snippet",id,pageToken)
        playlistViewModel.playlistitem.observe(viewLifecycleOwner, Observer {

            if (it.isSuccessful){
                playlistItemAdapter.playlistitemsetStateWiseTracker(it.body()?.items!!)
                pageToken = it.body()?.nextPageToken.toString()
            }


//            progressbar.visibility= View.GONE
            Log.d("Page 2","success"+it.toString())

        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = playlistItemAdapter


    }


}