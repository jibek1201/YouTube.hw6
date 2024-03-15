package com.example.youtube.ui.playlists

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.youtube.data.model.BaseResponse
import com.example.youtube.data.repository.YouTubeRepository
import com.example.youtube.data.service.Resource

class PlaylistsViewModel(private val repository: YouTubeRepository) : ViewModel() {
    fun getPlaylists(): LiveData<Resource<List<BaseResponse.Item>>> = repository.getPlaylists()
}