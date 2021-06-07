package com.example.flightsimulatorjoistick

import java.io.DataOutputStream
import java.net.Socket
import java.util.concurrent.Executors

class Model {
    private lateinit var socket: Socket
    private lateinit var dataOutputStream: DataOutputStream
    private val executor = Executors.newSingleThreadExecutor()
    fun connect(ip: String,port: String) {

        executor.execute(Runnable {
            try{

                this.socket = Socket(ip, port.toInt())
                this.dataOutputStream = DataOutputStream(socket.getOutputStream())

//            dout.writeUTF("1")
//            dout.flush()
//            dout.close()
//            soc.close()
            }
            catch (ex: Exception){
                println("exception")
            }
        })
    }
    fun setThrottle(value: Float) {

        executor.execute(Runnable {
            try {
                dataOutputStream.writeUTF("set /controls/flight/current-engine/throttle 1 \r\n")
                dataOutputStream.flush()
            } catch (ex: Exception) {
                println("exception")
            }
        })
    }
    fun setElevator(value: Float) {

        executor.execute(Runnable {
            try {
                dataOutputStream.writeUTF("set /controls/flight/elevator $value \r\n")
                dataOutputStream.flush()
            } catch (ex: Exception) {
                println("exception")
            }
        })
    }

    fun setRudder(value: Float) {
        executor.execute(Runnable {
            try {
                dataOutputStream.writeUTF("set /controls/flight/rudder $value \r\n")
                dataOutputStream.flush()
            } catch (ex: Exception) {
                println("exception")
            }
        })

    }
    fun setAileron(value: Float) {

        try {
            dataOutputStream.writeUTF("set /controls/flight/aileron $value \r\n")
            dataOutputStream.flush()
        } catch (ex: Exception) {
            println("exception")
        }
    }
}