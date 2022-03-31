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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youtubemvvm.BaseFragment
import com.example.youtubemvvm.R
import com.example.youtubemvvm.databinding.FragmentHomeBinding
import com.example.youtubemvvm.home.data.model.CompleteItem
import com.example.youtubemvvm.home.view.adapter.VideoAdapter
import com.example.youtubemvvm.home.viewmodel.HomeViewModel
import com.example.youtubemvvm.playvideo.PlayVideoActivity


class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter: VideoAdapter
    val timeSearch: Long = 0
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
        viewModel.transformationsCompleteItems.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                //hide progressbar
                adapter.submitList(it)
                hideProgressBar()
            }
        })
    }

    private fun setSearchView() {
        binding.searchViewVideo.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.getSearchVideo(query)
                return false
            }

            val lasttime = System.currentTimeMillis()
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText ?: "")
                return false
            }
        })
    }

    private fun onItemClickListener(completeItem: CompleteItem) {
        val intent = Intent(requireActivity(), PlayVideoActivity::class.java)
//        intent.putExtra("videoId", item.id.videoId)
        intent.putExtra("itemVideo", completeItem)
        startActivity(intent)
    }
}