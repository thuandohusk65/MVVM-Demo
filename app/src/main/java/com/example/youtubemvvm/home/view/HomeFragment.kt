package com.example.youtubemvvm.home.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubemvvm.BaseFragment
import com.example.youtubemvvm.R
import com.example.youtubemvvm.databinding.FragmentHomeBinding
import com.example.youtubemvvm.home.data.model.Item
import com.example.youtubemvvm.home.view.adapter.VideoAdapter
import com.example.youtubemvvm.home.viewmodel.HomeViewModel
import com.example.youtubemvvm.webview.WebViewVideoActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: VideoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearchView()
        showProgressBar()
        binding.recyclerViewVideo.layoutManager = LinearLayoutManager(requireContext())
        adapter = VideoAdapter()
        adapter.setOnItemClickListener { onItemClickListener(it) }
        binding.recyclerViewVideo.adapter = adapter
        viewModel.responseLiveData.observe(viewLifecycleOwner, Observer {
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

    private fun onItemClickListener(item: Item) {
        val bundle = Bundle()
        bundle.putString("videoId", item.id.videoId)
        val intent = Intent(requireActivity(), WebViewVideoActivity::class.java)
        intent.putExtra("videoId", item.id.videoId)
        startActivity(intent)
    }
}