package com.example.youtubemvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.youtubemvvm.databinding.ActivityMainBinding
import com.example.youtubemvvm.home.data.Videos
import com.example.youtubemvvm.home.data.datasource.RetrofitInstance
import com.example.youtubemvvm.home.data.datasource.VideoService
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
//        val navController = Navigation.findNavController(this,R.id.homeFragment)
//        binding.bottomNavigation.setupWithNavController(
//            navController
//        )

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.homeFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(
            navController
        )

        val  retrofitService = RetrofitInstance
            .getRetrofitInstance()
            .create(VideoService::class.java)

        val responseLiveData: LiveData<Response<Videos>> = liveData {
            val response =  retrofitService.getVideos()
            emit(response)
        }

        responseLiveData.observe(this, Observer {
            val videoList = it.body()?.items
            if (videoList != null) {
                for(item in videoList){
                    Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })


    }
}