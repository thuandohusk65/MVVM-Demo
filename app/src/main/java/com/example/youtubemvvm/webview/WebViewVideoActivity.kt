package com.example.youtubemvvm.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.youtubemvvm.R
import com.example.youtubemvvm.databinding.ActivityWebViewVideoBinding

class WebViewVideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_web_view_video)
        val videoId = intent.getStringExtra("videoId")
        if(videoId!!.isNotEmpty()){
            webViewSetup(videoId)
        }
        else {
            Toast.makeText(this,"Error! Please try again!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun webViewSetup(videoId: String){
        val webView = binding.webView
        webView.webViewClient =  WebViewClient()
        webView.apply {
            loadUrl("https://www.youtube.com/watch?v=${videoId}")
            settings.javaScriptEnabled = true
        }
    }
}