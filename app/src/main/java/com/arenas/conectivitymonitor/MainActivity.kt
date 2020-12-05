package com.arenas.conectivitymonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.internet)

        NetworkDetectorLiveData.observe(this, Observer {
            if (it){
                textView.text = "Internet"
            }
            else {
                textView.text = "No Internet"
            }
        })
    }
}