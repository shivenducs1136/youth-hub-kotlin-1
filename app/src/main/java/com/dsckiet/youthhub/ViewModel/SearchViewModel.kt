package com.dsckiet.youthhub.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.youthhub.Repo.Repository
import com.example.youthhub.searchapi.Item
import kotlinx.coroutines.launch
import retrofit2.Response

class SearchViewModel (private val repository: Repository): ViewModel() {

    var cityshowProgress: LiveData<Boolean>
    var searched: MutableLiveData<Response<Item>>

    init {
        this.cityshowProgress=repository.cityshowprogress
        this.searched= MutableLiveData()
    }

    fun getSearchItem(part:String,searchit:String){
        viewModelScope.launch {
            val SearchRes=repository.getSearchItem(part,searchit)
            searched.value=SearchRes
            Log.d("Searched",SearchRes.body().toString())
        }
    }
}