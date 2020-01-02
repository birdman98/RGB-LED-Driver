package com.driver.rgbleddriver

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val ColorPickerActivityRequestCode = 0

    private var espConnector: AsyncTask<String, Void, String>? = null
    private var espProperties: Pair<String, Int>? = null
    private var RGBChoice: Triple<Int, Int, Int>? = null
    private var isMusicReactiveMode: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val confirmationBtn = findViewById<Button>(R.id.confirmBtn)

        confirmationBtn.setOnClickListener { view ->
            var espIP = findViewById<EditText>(R.id.espIPInput)
            var espPort = findViewById<EditText>(R.id.espPortInput)

            this.espProperties = Pair(espIP.text.toString(), espPort.text.toString().toInt())

            Toast.makeText(this@MainActivity, "ESP properties set to: " + this.espProperties.toString(), Toast.LENGTH_SHORT).show()
        }

        val rgbPickerBtn = findViewById<Button>(R.id.rgbPickerBtn)

        rgbPickerBtn.setOnClickListener { view ->
            this.isMusicReactiveMode = false

            val colorPickerIntent = Intent(this, ColorPickerActivity::class.java)
            startActivityForResult(colorPickerIntent, this.ColorPickerActivityRequestCode)
        }

        val musicReactiveBtn = findViewById<Button>(R.id.musicReactiveBtn)

        musicReactiveBtn.setOnClickListener { view ->
            this.isMusicReactiveMode = true

            Toast.makeText(this@MainActivity, "Music reactive mode chosen!", Toast.LENGTH_SHORT).show()
        }

        val sendBtn = findViewById<Button>(R.id.sendBtn)

        sendBtn.setOnClickListener { view ->
            connectWithESPServer(getRequestContentToSend())
        }

    }

    private fun connectWithESPServer(requestToSend: String) {
        if(this.espConnector == null) {
            this.espConnector = ESPConnector(this.espProperties)
            this.espConnector!!.execute(requestToSend)
        }
        try {
            if(this.espConnector!!.status != AsyncTask.Status.RUNNING) {
                this.espConnector!!.cancel(true)
                this.espConnector = ESPConnector(this.espProperties)
                this.espConnector!!.execute(requestToSend)
            }
            Toast.makeText(this@MainActivity, "ESP replies: " + this.espConnector?.get(), Toast.LENGTH_SHORT).show()
        } catch(e: Exception) {
            Toast.makeText(this@MainActivity, "Exception " + e.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun getRequestContentToSend(): String {
        var requestContent = JSONObject()

        if(this.isMusicReactiveMode) {
            requestContent.put("mode", "music_reactive")
        } else {
            requestContent.put("mode", "RGB")
            requestContent.put("R", this.RGBChoice!!.first)
            requestContent.put("G", this.RGBChoice!!.second)
            requestContent.put("B", this.RGBChoice!!.third)
        }
        return requestContent.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == this.ColorPickerActivityRequestCode) {
            if(resultCode == Activity.RESULT_OK) {
                this.RGBChoice = data!!.getSerializableExtra("RGB") as Triple<Int, Int, Int>
                Toast.makeText(this@MainActivity, "RGB mode chosen!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
