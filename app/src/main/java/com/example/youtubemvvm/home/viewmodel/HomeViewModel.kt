package com.example.youtubemvvm.home.viewmodel

import androidx.lifecycle.*
import com.example.youtubemvvm.home.data.model.CompleteItem
import com.example.youtubemvvm.home.repository.GetItemRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

    private val searchKey = MutableStateFlow("")

    private val getItemRepository = GetItemRepository()

    private val getCompleteItems = MediatorLiveData<List<CompleteItem>>()

    val transformationsCompleteItems = Transformations.map(
        getCompleteItems
    ) { completeItem ->

        for(item in completeItem){
            item.videoItem.snippet.channelTitle = "${item.videoItem.snippet.channelTitle} VND group"
        }
        return@map completeItem
    }

    init {
        viewModelScope.launch {
            searchKey.collect {
                getSearchVideo(it)
            }
        }
    }

    fun getSearchVideo(searchKey: String?) = viewModelScope.launch {
        viewModelScope.launch {
            getItemRepository.getVideos(searchKey).let {
                if (it.isSuccessful) {
                    val completeItems = ArrayList<CompleteItem>()
                    val videoItems = it.body()!!.items
                    for (item in videoItems) {
                        val response = getItemRepository.getThumbs(item.snippet.channelId)
//                    Log.i("ABCD", response.await().toString())
                        if (response.isSuccessful) {
                            completeItems.add(CompleteItem(item, response.body()!!.items[0]))
                        }
                    }
                    getCompleteItems.postValue(completeItems)
                }
            }
        }
    }

    fun search(text: String) {
        searchKey.value = text
    }
}