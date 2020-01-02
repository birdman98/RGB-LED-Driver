package com.driver.rgbleddriver

import android.os.AsyncTask
import android.util.Log
import java.io.*
import java.net.Socket

class ESPConnector : AsyncTask<String, Void, String> {

    private val TAG: String = "ESPConnector"

    private var espIP: String
    private var espPort: Int
    private var espConnection: Socket? = null
    private var output: PrintWriter? = null
    private var input: BufferedReader? = null

    constructor(espProperties: Pair<String, Int>?) {
        this.espIP = espProperties!!.first
        this.espPort = espProperties!!.second
    }

    private fun initializeConnection(): Boolean {
        try {
            this.espConnection = Socket(this.espIP, this.espPort)
            this.input = BufferedReader(
                InputStreamReader(
                    this.espConnection!!.getInputStream()
                )
            )
            this.output = PrintWriter(
                BufferedWriter(
                    OutputStreamWriter(
                        this.espConnection!!.getOutputStream()
                    )
                ), true
            )
            return true
        } catch (e: Exception) {
            Log.e(TAG, "Cannot connect to ESP, reason: " + e.message)
            return false
        }
    }

    private fun sendRequest(message: String?) {
        if (message != null) {
            this.output!!.println(message)
            Log.d(TAG, "Sent to ESP: " + message)
        }
    }

    private fun readResponse(): String {
        var response: String = ""
        try {
            response = this.input!!.readLine()
        } catch (e: IOException) {
            Log.e(TAG, "Cannot read response from ESP, reason: " + e.message)
        }
        return response
    }

    private fun closeConnection() {
        if (this.espConnection != null) {
            this.espConnection!!.close()
        }
    }

    override fun doInBackground(vararg params: String?): String {
        if(initializeConnection()) {
            sendRequest(params[0])
            val response = readResponse()

            closeConnection()
            return response
        }

        return "Cannot connect to ESP!"
    }
}