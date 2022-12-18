package com.example.binderservice_demo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var btn : Button
    lateinit var txt : TextView

    var myService:MyHandler? = null
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.btn)
        txt = findViewById(R.id.txt)

        val intent = Intent(this, MyHandler::class.java)
        bindService(intent, mycon, Context.BIND_AUTO_CREATE)

        btn.setOnClickListener {

            val currentTime = myService?.getCurrentTime()
            txt.text = currentTime

        }
    }

        val mycon = object : ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder = service as MyHandler.MyLocalService
                myService = binder.getService()
                isBound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = false
            }
        }


}