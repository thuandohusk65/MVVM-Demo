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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubemvvm.R
import com.example.youtubemvvm.databinding.FragmentHomeBinding
import com.example.youtubemvvm.home.data.Videos
import com.example.youtubemvvm.home.data.datasource.RetrofitInstance
import com.example.youtubemvvm.home.data.datasource.VideoService
import com.example.youtubemvvm.home.view.adapter.VideoAdapter
import com.example.youtubemvvm.home.viewmodel.HomeViewModel
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: VideoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewVideo.layoutManager = LinearLayoutManager(requireContext())
        adapter = VideoAdapter()
        binding.recyclerViewVideo.adapter = adapter


        viewModel.responseLiveData.observe(this, Observer {
            val videoList = it.body()?.items
            if (videoList != null) {
                adapter.submitList(videoList)
            }
        })
    }
}