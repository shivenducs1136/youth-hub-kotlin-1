package com.example.youthhub.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.RoomDatabaseWork.Adapter.HomeOfflinePlaylistAdapter
import com.dsckiet.youthhub.RoomDatabaseWork.Entity.PlaylistEntity
import com.dsckiet.youthhub.RoomDatabaseWork.ViewModel.Playlist_ViewModel
import com.dsckiet.youthhub.databinding.FragmentHomeBinding
import android.widget.RelativeLayout
import androidx.recyclerview.widget.ItemTouchHelper
import com.dsckiet.youthhub.utils.SwipeGestures


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var SavedPlaylistviewModel: Playlist_ViewModel
    lateinit var  recyclerView:RecyclerView
    var isScrolling = false
    lateinit var topbar:RelativeLayout
    lateinit var adapter:HomeOfflinePlaylistAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         recyclerView = binding.homeRecview
        topbar = binding.homeTopbar

         adapter = HomeOfflinePlaylistAdapter(requireContext(),this)

        binding.addFab.setOnClickListener{

            findNavController().navigate(R.id.action_homeFragment_to_playlistFragment)

        }
        getdatafromROOMDB()

    }

    private fun getdatafromROOMDB() {
        swiped()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        SavedPlaylistviewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(Playlist_ViewModel::class.java)
        SavedPlaylistviewModel.allPlaylists.observe(viewLifecycleOwner, Observer {list ->

            list?.let{
                if(it.size == 0){
                    binding.noContent.visibility = View.VISIBLE
                }
                else {
                    binding.noContent.visibility = View.GONE
                    adapter.updatePlaylistList(it)
                }
            }
        })
        val offlinesharedPref = arguments
        val PlaylistTitle = offlinesharedPref?.getString("Playlist_title", "")
        val PlaylistChannelName = offlinesharedPref?.getString("Playlist_channel_name", "")
        val PlaylistThumbnail = offlinesharedPref?.getString("Playlist_ThumbNail", "")
        val PlaylistTotalVideos = offlinesharedPref?.getString("Playlist_No_Of_Videos", "")
        // Pass the Bundel and Insert the Data here
        if(PlaylistTitle!=null && PlaylistChannelName!=null && PlaylistThumbnail!=null && PlaylistTotalVideos !=null){
            Log.d("PlaylistTitle",PlaylistTitle.toString())
            Log.d("PlaylistChannelName",PlaylistChannelName.toString())
            Log.d("PlaylistThumbnail",PlaylistThumbnail.toString())
            Log.d("PlaylistTotalVideos",PlaylistTotalVideos.toString())
            if(PlaylistTitle!!.isNotEmpty() && PlaylistChannelName!!.isNotEmpty() && PlaylistThumbnail!!.isNotEmpty()&&PlaylistTotalVideos!!.isNotEmpty() ) {
                SavedPlaylistviewModel.insertPlaylist(PlaylistEntity(PlaylistTitle,PlaylistThumbnail,PlaylistChannelName,PlaylistTotalVideos,""))

            }

        }

    }

     fun swiped() {
        val swipeGestures = object :SwipeGestures(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT->{
                        adapter.deleteItem(viewHolder.adapterPosition)
                    }
                    ItemTouchHelper.RIGHT->{
                        adapter.deleteItem(viewHolder.adapterPosition)
                    }
                }

            }
        }
        val touchHelper = ItemTouchHelper(swipeGestures)
        touchHelper.attachToRecyclerView(recyclerView)
    }


    fun onItemClicked(playlistEntity: PlaylistEntity) {

        SavedPlaylistviewModel.deletePlaylist(playlistEntity)
        Toast.makeText(requireContext(),"Deleted",Toast.LENGTH_LONG).show()

    }

}
/*  recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true
                    topbar.animate()
                        .translationY(topbar.height.toFloat())
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                topbar.visibility = View.GONE
                            }
                        })
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(isScrolling){
                    isScrolling = false
                    topbar.animate()
                        .translationY(topbar.height.toFloat())
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                topbar.visibility = View.VISIBLE
                            }
                        })
                }else{
                    topbar.animate()
                        .translationY(topbar.height.toFloat())
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator?) {
                                super.onAnimationEnd(animation)
                                topbar.visibility = View.GONE
                            }
                        })
                }
            }
        })*/