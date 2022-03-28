package com.example.youtubemvvm

import android.app.AlertDialog
import android.os.Handler
import androidx.fragment.app.Fragment

open class BaseFragment(): Fragment() {
    lateinit var isDialog: AlertDialog
    fun showProgressBar(){
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.loading)
        isDialog = builder.create()
        isDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        isDialog.show()
    }

    fun hideProgressBar(){
        isDialog.dismiss()
    }
}