package com.example.youtubemvvm.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.youtubemvvm.home.data.Videos
import com.example.youtubemvvm.home.data.datasource.RetrofitInstance
import com.example.youtubemvvm.home.data.datasource.VideoService
import retrofit2.Response

class HomeViewModel: ViewModel() {
    val retrofitService = RetrofitInstance
        .getRetrofitInstance()
        .create(VideoService::class.java)

    val responseLiveData: LiveData<Response<Videos>> = liveData {
        val response = retrofitService.getVideos("")
        emit(response)
    }
}