package com.example.flightsimulatorjoistick

import android.view.View
import android.widget.Button

class ViewModel constructor(model :Model) {
    var ip: String = "IP"
    var port: String = "PORT"
    lateinit var model: Model

    init {
        this.model = model

    }
    fun connect(){
        //view.findViewById<Button>(R.id.connect).text = ip
        model.connect(ip,port)
    }

}