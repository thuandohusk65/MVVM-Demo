package com.example.youtubemvvm.home.data.datasource

import com.example.youtubemvvm.home.data.Videos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {
    @GET("v3/search?part=snippet&maxResults=50&key=AIzaSyBcXwHnDNxMx7G3vXR1b0b4K9yJz0q1oUE")
    suspend fun getVideos(@Query("q") searchKey: String? = ""): Response<Videos>
}