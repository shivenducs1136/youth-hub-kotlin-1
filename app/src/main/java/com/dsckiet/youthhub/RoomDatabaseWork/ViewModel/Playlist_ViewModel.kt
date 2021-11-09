package com.dsckiet.youthhub.RoomDatabaseWork.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.dsckiet.youthhub.RoomDatabaseWork.Database.PlaylistDataBase
import com.dsckiet.youthhub.RoomDatabaseWork.Entity.PlaylistEntity
import com.dsckiet.youthhub.RoomDatabaseWork.Repository.PlaylistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Playlist_ViewModel(application: Application): AndroidViewModel(application) {

    val allPlaylists: LiveData<List<PlaylistEntity>>
    private val repository: PlaylistRepository
    init{
        val dao = PlaylistDataBase.getDatabase(application).playlistDAO()
        repository = PlaylistRepository(dao)
        allPlaylists = repository.allPlaylists
    }

    fun deletePlaylist(playlistEntity: PlaylistEntity) = viewModelScope.launch(    Dispatchers.IO){

        repository.delete(playlistEntity)
    }

    fun insertPlaylist(playlistEntity: PlaylistEntity) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(playlistEntity)
    }

}