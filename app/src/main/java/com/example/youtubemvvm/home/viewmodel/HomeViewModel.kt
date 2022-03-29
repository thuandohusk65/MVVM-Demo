package com.example.youtubemvvm.home.viewmodel

import androidx.lifecycle.*
import com.example.youtubemvvm.home.data.model.Videos
import com.example.youtubemvvm.home.data.datasource.RetrofitInstance
import com.example.youtubemvvm.home.data.datasource.VideoService
import com.example.youtubemvvm.home.repository.GetVideoRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel() : ViewModel() {

    private val getVideoRepository = GetVideoRepository()
    val responseLiveData = MutableLiveData<Response<Videos>>()

    init {
        viewModelScope.launch {
            responseLiveData.postValue(getVideoRepository.getVideos(""))

        }
    }

    fun getSearchVideo(searchKey: String?) = viewModelScope.launch {
        responseLiveData.postValue(getVideoRepository.getVideos(searchKey))
    }
}