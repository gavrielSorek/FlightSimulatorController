package com.example.flightsimulatorjoistick

import android.view.View
import android.widget.Button

class ViewModel constructor(model :Model) {
    var ip: String = "IP"
    var port: String = "PORT"
    var throttle :Float = 0.0F
        set(value) {         // setter
            field = value
            model.setThrottle(value)
        }
    var aileron :Float = 0.0F
        set(value) {         // setter
            field = value
            model.setAileron(value)
        }
    var elevator :Float = 0.0F
        set(value) {         // setter
            field = value
            model.setElevator(value)
        }
    lateinit var model: Model

    init {
        this.model = model

    }
    fun connect(){
        //view.findViewById<Button>(R.id.connect).text = ip
        model.connect(ip,port)

    }

}