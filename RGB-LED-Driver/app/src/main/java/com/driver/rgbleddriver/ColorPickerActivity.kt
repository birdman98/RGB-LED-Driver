package com.driver.rgbleddriver

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class ColorPickerActivity : AppCompatActivity() {

    private var rColor: Int = 0
    private var gColor: Int = 0
    private var bColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker)

        val colorPreview = findViewById<TextView>(R.id.colorPreview)


        val rColorPicker = findViewById<SeekBar>(R.id.rColorPicker)

        rColorPicker.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                rColor = rColorPicker.progress
                setTextViewBackgroundColor(colorPreview)
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
                setTextViewBackgroundColor(colorPreview)
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
                setTextViewBackgroundColor(colorPreview)
                Toast.makeText(this@ColorPickerActivity, "Chosen value of B color is " + bColor, Toast.LENGTH_SHORT).show()
            }

        })

        val doneBtn = findViewById<Button>(R.id.doneBtn)

        doneBtn.setOnClickListener { view ->
            var RGB: Triple<Int, Int, Int> = Triple(rColor, gColor, bColor)

            val mainActivityIntent = Intent()
            mainActivityIntent.putExtra("RGB", RGB)
            setResult(Activity.RESULT_OK, mainActivityIntent)
            finish()
        }

    }

    private fun setTextViewBackgroundColor(textView: TextView) {
        textView.setBackgroundColor(Color.rgb(this.rColor, this.gColor, this.bColor))
    }

}
