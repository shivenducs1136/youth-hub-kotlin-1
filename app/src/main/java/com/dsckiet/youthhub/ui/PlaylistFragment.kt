package com.example.youthhub.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.ViewModel.PlaylistViewModel
import com.dsckiet.youthhub.ViewModel.PlaylistViewModelFactory
import com.dsckiet.youthhub.databinding.FragmentPlaylistBinding
import com.example.youthhub.Repo.Repository
import com.squareup.picasso.Picasso

class PlaylistFragment : Fragment() {

    private lateinit var binding: FragmentPlaylistBinding
    private lateinit var playlistViewModel: PlaylistViewModel
    private lateinit var playlistRepository: Repository
//    private lateinit var playlistAdapter:PlayListAdapter
    var id =""
    private lateinit var recyclerView:RecyclerView
    lateinit var no_of_vid:String
    lateinit var Playlist_title:String
    lateinit var Playlist_channel_name:String
    lateinit var Playlist_ThumbNail:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.id.searchAddedBtn.visibility = View.GONE
        binding.id.searchAddBtn.visibility = View.VISIBLE
        binding.playlistBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_playlistFragment_to_homeFragment)
        }
//        recyclerView= binding.playlistRecview
        binding.idkaid.visibility = View.GONE
        viewplaylistvialink()
        binding.idkaid.setOnClickListener {

            playlistclicked()

        }

    }

    private fun playlistclicked() {
       val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("PlaylistId", id)
            apply()
        }
        findNavController().navigate(R.id.action_playlistFragment_to_playlistOpenedFragment)

    }

    private fun viewplaylistvialink() {


        val repository=Repository(this.requireContext())
        val vmf= PlaylistViewModelFactory(repository)
        playlistViewModel = ViewModelProvider(this,vmf).get(PlaylistViewModel::class.java)

        var link =""
        var str=""
        var substr=""
        var beforelistinLink=""
        binding.playlistSearchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    link = query.toString()
                     str = link
                     substr = "list="
                     beforelistinLink = str.substring(0, str.indexOf(substr))
                    Log.e("BEFORE SSTRING : ",beforelistinLink)
                    id = str.substring(str.indexOf(substr) + substr.length)
                    Log.e("After SString : ",id)
                    playlistViewModel.getPlaylist("snippet",id)
                    playlistViewModel.playlist.observe(viewLifecycleOwner, Observer {
                        Log.e("ERROR",it.body().toString())
                        if(it.isSuccessful) {
                            binding.idkaid.visibility = View.VISIBLE
                            // addplaylist to home function
                            Picasso.with(context)
                                .load(it.body()?.items?.get(0)?.snippet?.thumbnails?.standard?.url)
                                .into(binding.id.searchPlaylistThumbnail)
                            binding.id.searchTitleIv.text = it.body()?.items?.get(0)?.snippet?.title
                            binding.id.playlistChannelNameIv.text =
                                it.body()?.items?.get(0)?.snippet?.channelTitle
                            playlistViewModel.getPlaylistItem("snippet", id, "")
                            playlistViewModel.playlistitem.observe(viewLifecycleOwner, Observer {
                                no_of_vid = it.body()?.pageInfo?.totalResults.toString()
                                binding.id.searchInPlaylistTotalVideos.text =
                                    it.body()?.pageInfo?.totalResults.toString() + " videos"
                            })
                            Playlist_title =it.body()?.items?.get(0)?.snippet?.title.toString()
                            Playlist_ThumbNail = it.body()?.items?.get(0)?.snippet?.thumbnails?.standard?.url.toString()
                            Playlist_channel_name =  it.body()?.items?.get(0)?.snippet?.channelTitle.toString()

                          binding.id.searchAddBtn.setOnClickListener {
                              binding.id.searchAddedBtn.visibility = View.VISIBLE
                              binding.id.searchAddBtn.visibility = View.GONE
                              val bundle = Bundle()

                              bundle.putString("Playlist_title", Playlist_title)
                              bundle.putString("Playlist_channel_name",Playlist_channel_name)
                              bundle.putString("Playlist_No_Of_Videos", no_of_vid)
                              bundle.putString("Playlist_ThumbNail", Playlist_ThumbNail)
                                findNavController().navigate(R.id.action_playlistFragment_to_homeFragment,bundle)
                                  Toast.makeText(requireContext(), "$Playlist_title added", Toast.LENGTH_LONG).show()
                            }
                        }

                    })
                    playlistViewModel.getPlaylistItem("snippet","",id)
                    playlistViewModel.playlistitem.observe(viewLifecycleOwner, Observer {
                        if (it.isSuccessful){
                            Log.e("Playlistitem",it.body()?.pageInfo?.totalResults.toString())
                            binding.id.searchInPlaylistTotalVideos.text = it.body()?.pageInfo?.totalResults.toString() + " videos"
                        }
                    })

                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            }
        )
    }

}