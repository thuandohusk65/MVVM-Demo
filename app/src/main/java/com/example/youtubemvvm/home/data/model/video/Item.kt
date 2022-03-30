package com.example.youtubemvvm.home.data.model.video


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item (
    @SerializedName("etag")
    val etag: String,
    @SerializedName("id")
    val id: Id,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("snippet")
    val snippet: Snippet
) :Serializable