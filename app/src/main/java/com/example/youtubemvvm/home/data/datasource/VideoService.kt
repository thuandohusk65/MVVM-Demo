package com.example.youtubemvvm.home.data.datasource

import com.example.youtubemvvm.home.data.model.channel.Channel
import com.example.youtubemvvm.home.data.model.video.Videos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {
    @GET("v3/search?part=snippet&maxResults=25&key=AIzaSyBcXwHnDNxMx7G3vXR1b0b4K9yJz0q1oUE")
    suspend fun getVideos(@Query("q") searchKey: String? = ""): Response<Videos>

    @GET("v3/channels?part=snippet&key=AIzaSyBcXwHnDNxMx7G3vXR1b0b4K9yJz0q1oUE")
    suspend fun getThumbChannel(@Query("id") id: String): Response<Channel>
}