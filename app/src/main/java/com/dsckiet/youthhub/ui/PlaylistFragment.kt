package com.example.youthhub.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.Adapters.PlayListAdapter
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.ViewModel.PlaylistViewModel
import com.dsckiet.youthhub.ViewModel.PlaylistViewModelFactory
import com.dsckiet.youthhub.databinding.FragmentPlaylistBinding
import com.example.youthhub.Repo.Repository

class PlaylistFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistBinding
    lateinit var viewModel:PlaylistViewModel
    private val playlistadapter:PlayListAdapter by lazy{PlayListAdapter(requireContext())}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.playlistBackBtn.setOnClickListener{
            findNavController().navigate(R.id.action_playlistFragment_to_homeFragment)
        }

        var link =""
        var str=""
        var substr=""
        var beforelistinLink=""
        var id =""
//        binding.playlistSearchView.setOnQueryTextListener(
//            object : SearchView.OnQueryTextListener{
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    link = query.toString()
//                     str = link
//                     substr = "list"
//                     beforelistinLink = str.substring(0, str.indexOf(substr))
//                    id = str.substring(str.indexOf(substr) + substr.length)
                    val repository = Repository(requireContext())
                    val viewModelFactory = PlaylistViewModelFactory(repository)
                    viewModel = ViewModelProvider(viewModelStore,viewModelFactory).get(PlaylistViewModel::class.java)
                    viewModel.getPlaylist("snippet","PLUcsbZa0qzu3Mri2tL1FzZy-5SX75UJfb")
                    viewModel.playlist.observe(viewLifecycleOwner, Observer {
                        playlistadapter.PlaylistStateWiseTracker(it)
//            binding..visibility = View.GONE

                        Log.d("BOLTy","success"+it.toString())

                    })
                    val recyclerView: RecyclerView = view.findViewById(R.id.playlist_recview)
                    recyclerView.layoutManager =
                        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    recyclerView.adapter = playlistadapter
//                    return false
//                }
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    return false
//                }
            }
//        )



    }


