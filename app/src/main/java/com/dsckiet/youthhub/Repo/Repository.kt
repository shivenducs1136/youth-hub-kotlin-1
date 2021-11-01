package com.example.youthhub.Repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dsckiet.youthhub.model.Item
import com.dsckiet.youthhub.model.Playlist
import com.dsckiet.youthhub.model.PlaylistDataClass
import com.dsckiet.youthhub.model.Snippet
import com.example.youthhub.Retrofit.RetrofitInstance
import retrofit2.Response


class Repository(var context: Context) {
    val showProgress = MutableLiveData<Boolean>()
    val cityshowprogress = MutableLiveData<Boolean>()


    suspend fun getPlaylistItem(part: String,id:String) :Response<Playlist>{
        return RetrofitInstance.api.getPlaylist(part,id)
    }
}