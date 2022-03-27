package com.example.youtubemvvm.home.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubemvvm.BaseFragment
import com.example.youtubemvvm.R
import com.example.youtubemvvm.databinding.FragmentHomeBinding
import com.example.youtubemvvm.home.view.adapter.VideoAdapter
import com.example.youtubemvvm.home.viewmodel.HomeViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response


class HomeFragment : BaseFragment() {
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
        setSearchView()
        showProgressBar()
        binding.recyclerViewVideo.layoutManager = LinearLayoutManager(requireContext())
        adapter = VideoAdapter()
        binding.recyclerViewVideo.adapter = adapter
        viewModel.responseLiveData.observe(this, Observer {
            val videoList = it.body()?.items
            if (!it.isSuccessful) {
                //hideprogressbar
                hideProgressBar()
                //show Toast, etc ...
            }
//            Log.i("ABCD", it.isSuccessful.toString())
            else if (videoList != null) {
                //hide progressbar
                    hideProgressBar()
                adapter.submitList(videoList)
            }
        })
    }

    private fun setSearchView() {
        binding.searchViewVideo.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getSearchVideo(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                MainScope().launch {
                    delay(2000)
                    viewModel.getSearchVideo(newText)
                }
                return false
            }
        }
        )
    }
}