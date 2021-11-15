package com.example.youthhub.ApiRequest

import com.dsckiet.youthhub.model.Playlist
import com.dsckiet.youthhub.model.PlaylistItem
import com.dsckiet.youthhub.videomodel.Item
import com.dsckiet.youthhub.videomodel.VideoDataClass
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
    @GET("/youtube/v3/playlistItems")
    suspend fun getPlaylistItem(
        @Query("part")part : String,
        @Query("playlistId") id:String,
        @Query("pageToken") pageToken:String,
        @Query("key") key:String = userapi
    ) : Response<PlaylistItem>

    @GET("/youtube/v3/videos")
    suspend fun getVideoinfo(
        @Query("part")part : String,
        @Query("id") id:String,
        @Query("key") key:String = userapi
    ) : Response<VideoDataClass>


//    https://youtube.googleapis.com/youtube/v3/playlists?part=snippet&id=PLu0W_9lII9aiL0kysYlfSOUgY5rNlOhUd&key=AIzaSyA6NwvjGob4DRwp2_ldwxwviebfE_DCIkk

}
