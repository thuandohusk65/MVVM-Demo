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
import com.example.youtubemvvm.home.data.model.CompleteItem


class VideoAdapter : ListAdapter<CompleteItem, VideoAdapter.ViewHolder>(VideoDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.video_item, parent, false)

        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textViewTitleVideo)
        val ImageViewChannel: ImageView = itemView.findViewById(R.id.imageViewChannel)
        val channeltitle: TextView = itemView.findViewById(R.id.textViewTitleChannel)
        val imageViewVideo: ImageView = itemView.findViewById(R.id.imageViewVideo)

        fun bind(completeItem: CompleteItem) {
            val res = itemView.context
            channeltitle.text = completeItem.videoItem.snippet.channelTitle
            title.text = completeItem.videoItem.snippet.title
            Glide.with(res).load(completeItem.channelItem.snippet.thumbnails.default.url)
                .into(ImageViewChannel)
            Glide.with(res).load(completeItem.videoItem.snippet.thumbnails.high.url)
                .into(imageViewVideo)
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(completeItem)
                }
            }
        }

    }

    private var onItemClickListener: ((CompleteItem) -> Unit)? = null

    fun setOnItemClickListener(onClickListener: (CompleteItem) -> Unit) {
        onItemClickListener = onClickListener
    }
}