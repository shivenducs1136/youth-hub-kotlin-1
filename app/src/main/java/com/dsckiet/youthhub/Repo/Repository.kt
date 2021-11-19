package com.example.youthhub.Repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dsckiet.youthhub.model.Item
import com.dsckiet.youthhub.model.Playlist
import com.dsckiet.youthhub.model.PlaylistItem
import com.dsckiet.youthhub.model.Snippet
import com.dsckiet.youthhub.videomodel.VideoDataClass
import com.example.youthhub.Retrofit.RetrofitInstance
import retrofit2.Response


class Repository(var context: Context) {
    val showProgress = MutableLiveData<Boolean>()
    val cityshowprogress = MutableLiveData<Boolean>()


    suspend fun getPlaylist(part: String,id:String) :Response<Playlist>{
        return RetrofitInstance.api.getPlaylist(part,id)
    }
    suspend fun getPlaylistItem(part: String,pageToken:String,playlistid:String) :Response<PlaylistItem>{
        return RetrofitInstance.api.getPlaylistItem(part,pageToken,playlistid)
    }
    suspend fun getVideoinfo(part: String,id:String) :Response<VideoDataClass>{
        return RetrofitInstance.api.getVideoinfo(part,id)
    }

}