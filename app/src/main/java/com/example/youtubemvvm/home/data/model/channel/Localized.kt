package com.example.youtubemvvm.home.data.model.channel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Localized(
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String
): Serializable