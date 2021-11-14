package com.dsckiet.youthhub.RoomDatabaseWork.Repository

import androidx.lifecycle.LiveData
import com.dsckiet.youthhub.RoomDatabaseWork.Dao.PlaylistDao
import com.dsckiet.youthhub.RoomDatabaseWork.Entity.PlaylistEntity

class PlaylistRepository(private  val playlistDao: PlaylistDao) {

    val allPlaylists: LiveData<List<PlaylistEntity>> = playlistDao.getplaylistdao()

    suspend fun insert(playlistEntity: PlaylistEntity){
        playlistDao.insert(playlistEntity)
    }
    suspend fun delete(playlistEntity: PlaylistEntity){
        playlistDao.delete(playlistEntity)
    }

}