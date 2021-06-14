package com.example.flightsimulatorcontroller.views
import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Window
import com.example.flightsimulatorcontroller.R
import java.util.*

class SuccessWindow : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success_window)
        var dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        var width = dm.widthPixels
        var height = dm.heightPixels
        window.setLayout((width*0.5).toInt(), (height*0.5).toInt())
        //set timer to close activity
        val timer = Timer()
        val t: TimerTask = object : TimerTask() {
            override fun run() {
                finish()
            }
        }
        timer.scheduleAtFixedRate(t, 1000, 1000)
    }

}