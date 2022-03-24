package com.example.youtubemvvm.home.data.datasource

import com.example.youtubemvvm.home.data.Videos
import retrofit2.Response
import retrofit2.http.GET

interface VideoService {
    @GET("/search?maxResults=25&q=surfing&key=AIzaSyBcXwHnDNxMx7G3vXR1b0b4K9yJz0q1oUE")
    suspend fun getVideos(): Response<Videos>
}