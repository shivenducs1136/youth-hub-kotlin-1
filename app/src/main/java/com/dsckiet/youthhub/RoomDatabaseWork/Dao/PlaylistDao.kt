package com.dsckiet.youthhub.RoomDatabaseWork.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dsckiet.youthhub.RoomDatabaseWork.Entity.PlaylistEntity

@Dao
interface PlaylistDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(playlistEntity: PlaylistEntity)

    @Delete
    suspend fun delete(playlistEntity: PlaylistEntity)

    @Query("SELECT * FROM Playlist_table")
    fun getplaylistdao(): LiveData<List<PlaylistEntity>>

}