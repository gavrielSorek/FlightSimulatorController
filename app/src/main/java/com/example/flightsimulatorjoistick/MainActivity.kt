package com.example.flightsimulatorjoistick

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private var viewModel: ViewModel = ViewModel(Model())
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.connect).setOnClickListener{
            connectClicked(it)
        }
        //binding
//        findViewById<TextView>(R.id.ip).apply {
//            text = viewModel.ip
//        }
//        findViewById<TextView>(R.id.port).apply {
//            text = viewModel.port
//        }
        var seekBar = findViewById<SeekBar>(R.id.seekBar2)

        var connectText = findViewById<TextView>(R.id.connect)


        seekBar.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                Log.d(TAG, "Moved , process data, Moved to :" + seekBar.progress)
                seekBar.progress = seekBar.progress
                connectText.text = seekBar.progress.toString()
                return@OnTouchListener false
            }
            Log.d(TAG, "Touched , Progress :" + seekBar.progress)
            true
        })


    }

    private fun connectClicked(view: View) {
        viewModel.ip = findViewById<TextView>(R.id.ip).text.toString()
        viewModel.port = findViewById<TextView>(R.id.port).text.toString()
        viewModel.connect()
    }
}