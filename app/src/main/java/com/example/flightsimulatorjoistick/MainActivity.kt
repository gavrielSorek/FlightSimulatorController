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
import androidx.databinding.DataBindingUtil
import com.example.flightsimulatorjoistick.databinding.ActivityMainBinding
import kotlin.reflect.KFunction


class MainActivity : AppCompatActivity() {
    private var viewModel: ViewModel = ViewModel(Model())
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        ).apply {
            this.setLifecycleOwner(this@MainActivity)
            this.viewmodel = viewModel
        }




        //binding
//        findViewById<TextView>(R.id.ip).apply {
//            text = viewModel.ip
//        }
//        findViewById<TextView>(R.id.port).apply {
//            text = viewModel.port
//        }
        var throttleSeekBar = findViewById<SeekBar>(R.id.throttleSeekBar)
        //throttle listener
        throttleSeekBar.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                viewModel.throttle = (throttleSeekBar.progress.toFloat() / 100)
                //connectText.text = throttleSeekBar.progress.toString()
                return@OnTouchListener false
            }
            Log.d(TAG, "Touched , Progress :" + throttleSeekBar.progress)
            true
        })
        var joystick = findViewById<Joystick>(R.id.joystick)
        joystick.joystickChangedAddFunction(::joystickOnChange)

        //rudder
        var rudderSeekBar = findViewById<SeekBar>(R.id.RudderSeekBar)
        rudderSeekBar.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                viewModel.rudder = (rudderSeekBar.progress.toFloat() / 100)
                return@OnTouchListener false
            }
            true
        })


    }
    private fun joystickOnChange(aileron: Float, elevator: Float) {

        viewModel.aileron = aileron
        viewModel.elevator = elevator
        println("ailron is: ${aileron} elevator is: ${elevator}")


    }
}