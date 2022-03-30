package com.example.youtubemvvm.home.viewmodel

import androidx.lifecycle.*
import com.example.youtubemvvm.home.data.model.CompleteItem
import com.example.youtubemvvm.home.data.model.video.Videos
import com.example.youtubemvvm.home.repository.GetVideoRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel() : ViewModel() {

    private val getVideoRepository = GetVideoRepository()

    //    private val getCompleteItem = MediatorLiveData<List<CompleteItem>>()
    val responseLiveData = MutableLiveData<Response<Videos>>()

    val getCompleteItem = Transformations.map(
        responseLiveData
    ) {
        val completeItems = ArrayList<CompleteItem>()
        if (it.isSuccessful) {
            viewModelScope.launch {
                val videoItems = it.body()!!.items
                for (item in videoItems) {
                    val response = getVideoRepository.getThumbs(item.snippet.channelId)
                    if (response.isSuccessful) {
                        completeItems.add(CompleteItem(item, response.body()!!.items[0]))
                    }
                }
            }
        }
        return@map completeItems
    }

    init {
        viewModelScope.launch {
            responseLiveData.postValue(getVideoRepository.getVideos(""))
        }
    }


    fun getSearchVideo(searchKey: String?) = viewModelScope.launch {
        responseLiveData.postValue(getVideoRepository.getVideos(searchKey))
    }
}