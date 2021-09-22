package com.dsckiet.youthhub.ApiRequest

import com.example.youthhub.searchapi.Item
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApiRequest {
    companion object{
        const val userapi:String="AIzaSyA6NwvjGob4DRwp2_ldwxwviebfE_DCIkk"
    }
    @GET("search")
    suspend fun getSearchItem(
        @Query("part")part : String,
        @Query("q") searchit:String,

    ) : Response<Item>


}
//https://youtube.googleapis.com/youtube/v3/search?part=snippet&q=carry&key=AIzaSyA6NwvjGob4DRwp2_ldwxwviebfE_DCIkk