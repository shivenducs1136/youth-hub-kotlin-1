package com.example.youthhub.ApiRequest

import com.dsckiet.youthhub.model.Item
import com.dsckiet.youthhub.model.Snippet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiRequest {
    companion object{
        const val userapi:String="AIzaSyA6NwvjGob4DRwp2_ldwxwviebfE_DCIkk"
    }
    @GET("part=&id=&key=$userapi")
    suspend fun getPlaylist(
        @Query("part")part : String,
        @Query("id") id:String,

    ) : List<Item.Snippet>


}
//https://youtube.googleapis.com/youtube/v3/search?part=snippet&q=carry&key=AIzaSyA6NwvjGob4DRwp2_ldwxwviebfE_DCIkk