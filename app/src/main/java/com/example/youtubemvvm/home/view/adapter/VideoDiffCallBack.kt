package com.example.youtubemvvm.home.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.youtubemvvm.home.data.model.CompleteItem
import com.example.youtubemvvm.home.data.model.video.Item

class VideoDiffCallBack: DiffUtil.ItemCallback<CompleteItem>() {
    override fun areItemsTheSame(oldItem: CompleteItem, newItem: CompleteItem): Boolean {
        return oldItem.videoItem.id.videoId == newItem.videoItem.id.videoId
    }

    override fun areContentsTheSame(oldItem: CompleteItem, newItem: CompleteItem): Boolean {
        return oldItem == newItem
    }
}