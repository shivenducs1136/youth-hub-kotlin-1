package com.example.youthhub.Repo

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.youthhub.ApiRequest.SearchApiRequest
import com.example.youthhub.Retrofit.RetrofitInstance
import com.example.youthhub.searchapi.Item
import com.example.youthhub.searchapi.Snippet
import retrofit2.Response


class Repository(var context: Context) {
    val showProgress = MutableLiveData<Boolean>()
    val cityshowprogress = MutableLiveData<Boolean>()


    suspend fun getSearchItem(part: String,searchit:String) : Response<Item>{
        return RetrofitInstance.api.getSearchItem(part,searchit)
    }
}