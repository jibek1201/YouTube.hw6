package com.example.youtube.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtube.BuildConfig
import com.example.youtube.data.model.BaseResponse
import com.example.youtube.data.model.PlaylistItemsModel
import com.example.youtube.data.service.Resource
import com.example.youtube.data.service.YouTubeApiService
import com.example.youtube.utils.YouTubeKeys
import kotlinx.coroutines.Dispatchers

class YouTubeRepository(
    private val service: YouTubeApiService
) {
    fun getPlaylists(): LiveData<Resource<List<BaseResponse.Item>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = service.getPlaylists(
                BuildConfig.API_KEY,
                YouTubeKeys.CHANNEL_ID,
                YouTubeKeys.PART,
                YouTubeKeys.MAX_RESULTS
            )
            if (response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success(it.items))
                }
            }
        }catch (ex:Exception){
            emit(Resource.Error(ex.localizedMessage ?: "Unknown Error"))
        }
    }

    fun getPlaylistItems(getId:String, count: Int):LiveData<Resource<List<PlaylistItemsModel.Item>>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = service.getPlaylistItems(
                BuildConfig.API_KEY,
                getId,
                count,
                YouTubeKeys.PART,
            )
            if (response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success(it.items))
                }
            }
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }
}