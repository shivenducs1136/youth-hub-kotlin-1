package com.example.youthhub.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.youthhub.Repo.Repository


class PlaylistOpenedFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistOpenedBinding
    private lateinit var playlistViewModel: PlaylistViewModel
    private lateinit var playlistItemAdapter: PlaylistItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_playlist_opened,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_playlistOpenedFragment_to_playlistFragment)
        }
        viewvideosofplaylist()
    }

    private fun viewvideosofplaylist() {
        val recyclerView: RecyclerView = binding.playlistOpenedRecview
        val repository= Repository(this.requireContext())
        val vmf= PlaylistViewModelFactory(repository)
        playlistViewModel = ViewModelProvider(this,vmf).get(PlaylistViewModel::class.java)

        val bundle = this.arguments
        val id = bundle?.get("playlistid").toString()
        playlistViewModel.getPlaylistItem("snippet",id)
        Log.e("PlaylistOPFRAGMENTID" , id)
        playlistViewModel.getPlaylist("snippet",id)


        playlistItemAdapter = PlaylistItemAdapter(requireContext())

/*
        playlistViewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it)
                progressbar.visibility = View.VISIBLE
            else
                progressbar.visibility = View.GONE
        })
*/
        playlistViewModel.playlist.observe(viewLifecycleOwner, Observer {
            if(it.isSuccessful){
                binding.playOpenedPlaytitle.text = it.body()?.items?.get(0)?.snippet?.title
            }
        })
        playlistViewModel.playlistitem.observe(viewLifecycleOwner, Observer {

            if (it.isSuccessful)
                playlistItemAdapter.playlistitemsetStateWiseTracker(it.body()?.items!!)


//            progressbar.visibility= View.GONE
            Log.d("BOLT","success"+it.toString())

        })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = playlistItemAdapter
    }


}