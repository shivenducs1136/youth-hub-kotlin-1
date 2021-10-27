package com.dsckiet.youthhub.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.youthhub.Repo.Repository
import com.example.youthhub.ViewModel.SearchViewModel

class PlaylistViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistViewModel(repository) as T
    }
}