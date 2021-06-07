package com.example.flightsimulatorjoistick



class ViewModel constructor(model :Model) {
    var ip: String = ""

    var port: String = ""
    var throttle :Float = 0.0F
        set(value) {         // setter
            field = value
            model.setThrottle(value)
        }
    var rudder :Float = 0.0F
        set(value) {         // setter
            field = value
            model.setRudder(value)
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
    var model: Model = model
    fun connect(){
        //view.findViewById<Button>(R.id.connect).text = ip
        model.connect(ip.toString(),port.toString())

    }

}