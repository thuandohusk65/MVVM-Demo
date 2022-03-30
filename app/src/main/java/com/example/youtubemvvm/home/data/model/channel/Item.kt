package com.example.youtubemvvm.home.data.model.channel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    @SerializedName("etag")
    val etag: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("snippet")
    val snippet: Snippet
): Serializable