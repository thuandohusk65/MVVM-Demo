package com.example.youtubemvvm.savedvideo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.youtubemvvm.R
import com.example.youtubemvvm.databinding.FragmentSavedVideoBinding

class SavedVideoFragment : Fragment() {
    private lateinit var binding: FragmentSavedVideoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_saved_video, container, false)
        return binding.root
    }

}