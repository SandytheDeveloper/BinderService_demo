package com.example.binderservice_demo

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.*

class MyHandler:Service() {

    var mybinder=MyLocalService()
    override fun onBind(p0: Intent?): IBinder? {
        return mybinder
    }

    fun getCurrentTime(): String{
        val dateformat = SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US)
        return dateformat.format(Date())
    }

    inner class MyLocalService: Binder(){
        fun getService():MyHandler{

            return this@MyHandler
        }

    }

}