package com.example.flightsimulatorjoistick


import android.view.View
import android.widget.Button
import androidx.databinding.Bindable

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.MutableLiveData

class ViewModel constructor(model :Model) {
    @Bindable
    var ip: MutableLiveData<String> = MutableLiveData<String>()

    @Bindable
    var port: MutableLiveData<String> = MutableLiveData<String>()
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
        model.connect(ip.toString(),port.toString())

    }

}