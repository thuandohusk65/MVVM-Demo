package com.example.youtubemvvm.home.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubemvvm.R
import com.example.youtubemvvm.home.data.Item



class VideoAdapter: ListAdapter<Item, VideoAdapter.ViewHolder>(VideoDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.textViewVideo)
        val imageViewVideo: ImageView = itemView.findViewById(R.id.imageViewVideo)

        fun bind(item: Item) {
            val res = itemView.context
            title.text = item.snippet.title
            Glide.with(res).load(item.snippet.thumbnails.high.url)
                .into(imageViewVideo)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.video_item, parent, false)

                return ViewHolder(view)
            }
        }
    }
}