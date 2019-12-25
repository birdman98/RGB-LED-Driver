package com.driver.rgbleddriver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val musicReactiveBtn: Button = findViewById(R.id.musicReactiveBtn)
        val rgbPickerBtn: Button = findViewById(R.id.rgbPickerBtn)

        musicReactiveBtn.setOnClickListener { view ->
            val espConnector: ESPConnector = ESPConnector("127.0.0.1", 1560)
            espConnector.initializeConnection()
            espConnector.run()

        }

    }
}
