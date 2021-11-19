package com.dsckiet.youthhub.RoomDatabaseWork.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Playlist_table")
class PlaylistEntity (
    @PrimaryKey
    @ColumnInfo(name = "Playlist_Title") val Playlist_Title: String,
    @ColumnInfo(name = "Playlist_Thumbnail") val Playlist_Thumbnail: String,
    @ColumnInfo(name = "Playlist_Channel_Name") val Playlist_Channel_Name: String,
    @ColumnInfo(name ="Number_Of_Videos") val Number_Of_Videos:String,
    @ColumnInfo(name ="Playlist_Channel_DP") val Playlist_Channel_DP:String,
    @ColumnInfo(name ="Playlist_ID") val Playlist_ID:String)