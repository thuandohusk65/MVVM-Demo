package com.example.youtubemvvm.home.viewmodel

import androidx.lifecycle.*
import com.example.youtubemvvm.home.data.Videos
import com.example.youtubemvvm.home.data.datasource.RetrofitInstance
import com.example.youtubemvvm.home.data.datasource.VideoService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    val retrofitService = RetrofitInstance
        .getRetrofitInstance()
        .create(VideoService::class.java)

    val responseLiveData = MutableLiveData<Response<Videos>>()

    init {
        viewModelScope.launch {
            responseLiveData.postValue(retrofitService.getVideos())

        }
    }

    fun getSearchVideo(searchKey: String?) = viewModelScope.launch {
        responseLiveData.postValue(retrofitService.getVideos(searchKey))
    }
}