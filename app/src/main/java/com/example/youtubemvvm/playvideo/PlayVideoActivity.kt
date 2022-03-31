package com.example.youtubemvvm.playvideo

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.youtubemvvm.R
import com.example.youtubemvvm.databinding.ActivityPlayVideoBinding
import com.example.youtubemvvm.home.data.model.CompleteItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class PlayVideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayVideoBinding
    private lateinit var isDialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_play_video)
        lifecycle.addObserver(binding.youtubePlayerView)
        showProgressBar()
        val completeItem = intent.getSerializableExtra("itemVideo") as CompleteItem
        if(completeItem!=null){
            setupYoutubePlayerView(completeItem.videoItem.id.videoId)
            binding.textViewTitleVideo.text = completeItem.videoItem.snippet.title
            binding.textViewChannelName.text = completeItem.videoItem.snippet.channelTitle
            Glide.with(this).load(completeItem.channelItem.snippet.thumbnails.default.url)
                .into(binding.imageViewChannel)
        }
        else {
            Toast.makeText(this,"Error! Please try again!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupYoutubePlayerView(videoId: String){
        binding.youtubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(videoId,0F)
                hideProgressBar()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun showProgressBar(){
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.loading)
        isDialog = builder.create()
        isDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        isDialog.show()
    }

    fun hideProgressBar(){
        isDialog.dismiss()
    }
}