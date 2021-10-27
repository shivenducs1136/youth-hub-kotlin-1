package com.example.youthhub.Repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.dsckiet.youthhub.model.Item
import com.example.youthhub.Retrofit.RetrofitInstance
import retrofit2.Response


class Repository(var context: Context) {
    val showProgress = MutableLiveData<Boolean>()
    val cityshowprogress = MutableLiveData<Boolean>()


    suspend fun getSearchItem(part: String,id:String) : List<Item.Snippet>{
        return RetrofitInstance.api.getPlaylist(part,id)
    }
}