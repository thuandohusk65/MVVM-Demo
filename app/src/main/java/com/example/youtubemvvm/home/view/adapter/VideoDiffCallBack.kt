package com.example.youtubemvvm.home.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.youtubemvvm.home.data.model.Item

class VideoDiffCallBack: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id.videoId == newItem.id.videoId
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}