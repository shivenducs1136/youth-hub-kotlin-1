package com.example.youthhub.ApiRequest

import com.dsckiet.youthhub.model.Item
import com.dsckiet.youthhub.model.Playlist
import com.dsckiet.youthhub.model.PlaylistDataClass
import com.dsckiet.youthhub.model.Snippet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiRequest {
    companion object{
        const val userapi:String="AIzaSyA6NwvjGob4DRwp2_ldwxwviebfE_DCIkk"
    }

    @GET("/youtube/v3/playlists")
    suspend fun getPlaylist(
        @Query("part")part : String,
        @Query("id") id:String,
        @Query("key") key:String = userapi

    ) : Response<Playlist>

//    https://youtube.googleapis.com/youtube/v3/playlists?part=snippet&id=PLu0W_9lII9aiL0kysYlfSOUgY5rNlOhUd&key=AIzaSyA6NwvjGob4DRwp2_ldwxwviebfE_DCIkk

}
