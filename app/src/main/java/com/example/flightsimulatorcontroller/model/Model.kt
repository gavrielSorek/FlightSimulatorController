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
    //private lateinit var dataOutputStream: DataOutputStream
    private val executor = Executors.newSingleThreadExecutor()
    fun connect(ip: String,port: String) {
        executor.execute(Runnable {
            try{

                if(this.socket == null) { //in new socket
                    println("ip ${ip}, port: ${port}")
                    this.socket = Socket()
                    this.socket!!.connect(InetSocketAddress(ip, port.toInt()), 6000)

                }else { //close the old connection
                    this.socket?.close()
                    println("ip ${ip}, port: ${port}")
                    this.socket = Socket()
                    this.socket!!.connect(InetSocketAddress(ip, port.toInt()), 6000)
                }
            }
            catch (ex: Exception){
                this.socket = null
                errorHandler?.let { it() }
                println("exception")
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
                println("throttle is :${value}")
                socket!!.outputStream.write("set /controls/engines/current-engine/throttle $value \r\n".toByteArray())
                socket!!.outputStream?.flush()
            } catch (ex: Exception) {
                println("exception")
            }
        })
    }
    fun setElevator(value: Float) {

        executor.execute(Runnable {
            try {
                println("elevator is :${value}")
                socket!!.outputStream.write("set /controls/flight/elevator $value \r\n".toByteArray())
                socket!!.outputStream.flush()
            } catch (ex: Exception) {
                println("exception")
            }
        })
    }

    fun setRudder(value: Float) {
        executor.execute(Runnable {
            try {
                println("rudder is :${value}")
                socket!!.outputStream.write("set /controls/flight/rudder $value \r\n".toByteArray())
                socket!!.outputStream.flush()
            } catch (ex: Exception) {
                println("exception")
            }
        })

    }
    fun setAileron(value: Float) {
        executor.execute(Runnable {
            try {
                println("aileron is :${value}")
                socket!!.outputStream.write("set /controls/flight/aileron $value \r\n".toByteArray())
                socket!!.outputStream.flush()
            } catch (ex: Exception) {
                println("exception")
            }
        })
    }
}