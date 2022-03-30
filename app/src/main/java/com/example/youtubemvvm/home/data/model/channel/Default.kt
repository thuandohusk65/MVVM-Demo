package com.example.youtubemvvm.home.data.model.channel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Default(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
): Serializable