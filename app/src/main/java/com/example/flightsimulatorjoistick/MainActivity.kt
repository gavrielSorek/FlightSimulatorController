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

        //rudder
        var rudderSeekBar = findViewById<SeekBar>(R.id.RudderSeekBar)
        rudderSeekBar.setOnTouchListener(OnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_MOVE) {
                viewModel.rudder = ((rudderSeekBar.progress.toFloat() / 50)-1)
                return@OnTouchListener false
            }
            true
        })

        //var connectButton = findViewById<Button>(R.id.connect)

        //set listeners
        var throttleSeekBar = findViewById<com.lukelorusso.verticalseekbar.VerticalSeekBar>(R.id.throttleSeekBar)
        throttleSeekBar.setOnProgressChangeListener{progress -> viewModel.throttle = (progress.toFloat() / 100) }

        var joystick = findViewById<Joystick>(R.id.joystick)
        joystick.joystickChangedAddFunction(::joystickOnChange)

    }
    private fun joystickOnChange(aileron: Float, elevator: Float) {

        viewModel.aileron = aileron
        viewModel.elevator = -1*elevator
       // println("ailron is: ${aileron} elevator is: ${elevator}")

    }

}