package com.example.youtubemvvm.home.repository

import com.example.youtubemvvm.home.data.datasource.RetrofitInstance
import com.example.youtubemvvm.home.data.datasource.VideoService

class GetItemRepository() {
    private val retrofitService = RetrofitInstance
        .getRetrofitInstance()
        .create(VideoService::class.java)

    suspend fun getVideos(searchKey: String?) =
        retrofitService.getVideos(searchKey)

    suspend fun getThumbs(channelId: String) =
        retrofitService.getThumbChannel(channelId)
}
