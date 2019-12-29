package com.driver.rgbleddriver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast

class ColorPickerActivity : AppCompatActivity() {

    private var rColor: Int = 0
    private var gColor: Int = 0
    private var bColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker)


        val rColorPicker = findViewById<SeekBar>(R.id.rColorPicker)

        rColorPicker.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                rColor = rColorPicker.progress
                Toast.makeText(this@ColorPickerActivity, "Chosen value of R color is " + rColor, Toast.LENGTH_SHORT).show()
            }
        })

        val gColorPicker = findViewById<SeekBar>(R.id.gColorPicker)

        gColorPicker.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                gColor = gColorPicker.progress
                Toast.makeText(this@ColorPickerActivity, "Chosen value of G color is " + gColor, Toast.LENGTH_SHORT).show()
            }

        })

        val bColorPicker = findViewById<SeekBar>(R.id.bColorPicker)

        bColorPicker.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                bColor = bColorPicker.progress
                Toast.makeText(this@ColorPickerActivity, "Chosen value of B color is " + bColor, Toast.LENGTH_SHORT).show()
            }

        })

    }

}
