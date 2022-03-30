package com.example.youtubemvvm.home.data.model.video


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Medium(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
):Serializable