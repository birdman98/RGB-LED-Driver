package com.driver.rgbleddriver

import android.util.Log
import java.io.*
import java.net.Socket
import java.util.*

class ESPConnector {
    private val TAG: String = "ESPConnector"
    private val espIP: String
    private val espPORT: Int
    private var espConnection: Socket? = null
    private var output: OutputStream? = null
    private var input: Scanner? = null
    private var isConnected: Boolean = false


    constructor(ip: String, port: Int) {
        this.espIP = ip
        this.espPORT = port
    }

    public fun initializeConnection() {
        try {
            this.espConnection = Socket(espIP, espPORT)
        } catch (e: Exception) {
            Log.e(TAG,"Cannot connect to ESP, reason: " + e.message)
        }

    }

    public fun sendResponse(message: String?) {
        if(message != null) {
            try {
                Log.d(TAG, "Sending to ESP: " + message)
                output!!.write(message.toByteArray())
                output!!.flush()
            } catch (e: IOException) {
                Log.e(TAG, "Failed to send data: " + e.message)
            }
        }

    }

    public fun run() {
        try {
            this.output = espConnection!!.getOutputStream()
            this.input = Scanner(espConnection!!.getInputStream())
        } catch (e: Exception) {

        }
    }

}