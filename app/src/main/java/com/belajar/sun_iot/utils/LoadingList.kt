package com.belajar.sun_iot.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.TextViewCompat

object LoadingList {

    fun showLoading(status: Int ,pb: ProgressBar){
        if (status == 0){
            pb.visibility = View.INVISIBLE
        }else if (status == 1){
            pb.visibility = View.VISIBLE
        }

    }



}