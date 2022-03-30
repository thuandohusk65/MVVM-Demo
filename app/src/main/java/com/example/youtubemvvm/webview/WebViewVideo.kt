package com.example.youtubemvvm.webview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.youtubemvvm.databinding.FragmentWebviewVideoBinding


class WebViewVideo : Fragment() {
    private lateinit var binding: FragmentWebviewVideoBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentWebviewVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videoId = arguments?.getString("videoId")
        if(videoId!!.isNotEmpty()){
        }
        else {
            Toast.makeText(requireContext(),"Error! Please try again!", Toast.LENGTH_SHORT).show()
        }
    }



}