package com.example.youtubemvvm.home.data.model

import com.example.youtubemvvm.home.data.model.video.Item
import java.io.Serializable


data class CompleteItem(
    val videoItem: Item,
    val channelItem: com.example.youtubemvvm.home.data.model.channel.Item
): Serializable