package com.dsckiet.youthhub.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsckiet.youthhub.model.Playlist
import com.dsckiet.youthhub.model.PlaylistItem
import com.dsckiet.youthhub.videomodel.VideoDataClass
import com.example.youthhub.Repo.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class PlaylistViewModel (private val repository: Repository): ViewModel() {

    var PlaylistshowProgress: LiveData<Boolean>
    var playlist: MutableLiveData<Response<Playlist>>
    var playlistitem: MutableLiveData<Response<PlaylistItem>>
    var videoinfo: MutableLiveData<Response<VideoDataClass>>

    init {
        this.PlaylistshowProgress=repository.cityshowprogress
        this.playlist= MutableLiveData()
        this.playlistitem = MutableLiveData()
        this.videoinfo = MutableLiveData()
    }

    fun getPlaylist(part:String,id:String){
        viewModelScope.launch {
            val SearchRes=repository.getPlaylist(part,id)
            playlist.value=SearchRes
            Log.e("Searched",SearchRes.toString())
        }
    }
    fun getPlaylistItem(part:String,pageToken:String,id:String){
        viewModelScope.launch {
            val ItemSearchRes=repository.getPlaylistItem(part,pageToken,id)
            playlistitem.value=ItemSearchRes
            Log.e("Playlist item Searched",ItemSearchRes.toString())
        }
    }
    fun getVideoinfo(part:String,id:String){
        viewModelScope.launch {
            val videoinfores=repository.getVideoinfo(part,id)
            videoinfo.value=videoinfores
            Log.e(" Video info",videoinfores.toString())
        }
    }
}