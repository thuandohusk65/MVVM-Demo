package com.example.youtubemvvm.home.data.model.channel


import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("pageInfo")
    val pageInfo: PageInfo
)