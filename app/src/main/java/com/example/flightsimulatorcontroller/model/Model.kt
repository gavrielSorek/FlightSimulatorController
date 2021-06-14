package com.example.flightsimulatorcontroller.model

import android.R.attr
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Executors


class Model {
    private var socket : Socket? = null
    var errorHandler : (() -> Unit)? = null
    set(value) {         // setter
        field = value
    }
    var successHandler : (() -> Unit)? = null
        set(value) {         // setter
            field = value
        }
    private val executor = Executors.newSingleThreadExecutor()
    fun connect(ip: String,port: String) {
        executor.execute(Runnable {
            try{

                if(this.socket == null) { //in new socket
                    this.socket = Socket()
                    this.socket!!.connect(InetSocketAddress(ip, port.toInt()), 5000)
                    successHandler?.let { it() }

                }else { //close the old connection
                    this.socket?.close()
                    this.socket = Socket()
                    this.socket!!.connect(InetSocketAddress(ip, port.toInt()), 5000)
                    successHandler?.let { it() }
                }
            }
            catch (ex: Exception){
                this.socket = null
                errorHandler?.let { it() }
            }
        })
    }
    fun disconnect() {
        executor.execute(Runnable {
            this.socket?.close()
            this.socket = null
        })
    }



    fun setThrottle(value: Float) {
        executor.execute(Runnable {
            try {
                socket!!.outputStream.write("set /controls/engines/current-engine/throttle $value \r\n".toByteArray())
                socket!!.outputStream?.flush()
            } catch (ex: Exception) { //do nothing
            }
        })
    }
    fun setElevator(value: Float) {

        executor.execute(Runnable {
            try {
                socket!!.outputStream.write("set /controls/flight/elevator $value \r\n".toByteArray())
                socket!!.outputStream.flush()
            } catch (ex: Exception) { //do nothing
            }
        })
    }

    fun setRudder(value: Float) {
        executor.execute(Runnable {
            try {
                socket!!.outputStream.write("set /controls/flight/rudder $value \r\n".toByteArray())
                socket!!.outputStream.flush()
            } catch (ex: Exception) { //do nothing
            }
        })

    }
    fun setAileron(value: Float) {
        executor.execute(Runnable {
            try {
                socket!!.outputStream.write("set /controls/flight/aileron $value \r\n".toByteArray())
                socket!!.outputStream.flush()
            } catch (ex: Exception) { //do nothing
            }
        })
    }
}