package com.driver.rgbleddriver

import android.os.AsyncTask
import android.util.Log
import java.io.*
import java.net.Socket

class ESPConnector : AsyncTask<Pair<String, Int>, Void, String>() {

    private val TAG: String = "ESPConnector"

    private var espConnection: Socket? = null
    private var output: PrintWriter? = null
    private var input: BufferedReader? = null

    private fun initializeConnection(ip: String, port: Int) {
        try {
            this.espConnection = Socket(ip, port)
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
        } catch (e: Exception) {
            Log.e(TAG, "Cannot connect to ESP, reason: " + e.message)
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

    override fun doInBackground(vararg params: Pair<String, Int>?): String {
        initializeConnection(params[0]!!.first, params[0]!!.second)

        sendRequest("")
        val response = readResponse()

        closeConnection()
        return response
    }

}