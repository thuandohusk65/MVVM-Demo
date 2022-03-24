package com.example.youtubemvvm.home.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.youtubemvvm.R
import com.example.youtubemvvm.databinding.FragmentHomeBinding
import com.example.youtubemvvm.home.data.Videos
import com.example.youtubemvvm.home.data.datasource.RetrofitInstance
import com.example.youtubemvvm.home.data.datasource.VideoService
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root

    }
}