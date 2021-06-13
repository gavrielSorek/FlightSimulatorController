package com.example.flightsimulatorcontroller.views

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Window
import com.example.flightsimulatorcontroller.R

class ErrorWindow : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.error_window)
        var dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        var width = dm.widthPixels
        var height = dm.heightPixels
        window.setLayout((width*0.5).toInt(), (height*0.5).toInt())
    }

}