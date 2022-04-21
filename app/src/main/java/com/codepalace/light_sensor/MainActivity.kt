package com.codepalace.light_sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class MainActivity : AppCompatActivity(), SensorEventListener {

    /* variable sensor dengan di lateinit */
    private lateinit var sensorManager: SensorManager
    private var brightness: Sensor? = null

    /* memberi nama variabel dengan lateinit */
    private lateinit var text: TextView
    private lateinit var pb: CircularProgressBar
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        text = findViewById(R.id.tv_text)
        pb = findViewById(R.id.circularProgressBar)
        btnStart = findViewById(R.id.idbtn_start)
        btnStop = findViewById(R.id.idbtn_stop)

        setUpSensorStuff()

        btnStart.setOnClickListener {
            sensorManager.registerListener(this, brightness, SensorManager.SENSOR_DELAY_NORMAL)
            btnStart.isEnabled = false
            btnStop.isEnabled = true
        }

        btnStop.setOnClickListener {
            sensorManager.unregisterListener(this)
            btnStart.isEnabled = true
            btnStop.isEnabled = false
            text.setText("Klik Tombol Start")
        }
    }

    private fun setUpSensorStuff() {
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        brightness = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
    }

    /* function override from SensorEventListener */
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            val light1 = event.values[0]

            text.text = "Sensor: $light1\n${brightness(light1)}"
            pb.setProgressWithAnimation(light1)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }
    /**/

    private fun brightness(brightness: Float): String {

        return when (brightness.toInt()) {
            0 -> "Gelap Gulita"
            in 1..10 -> "Gelap"
            in 11..50 -> "Lumayan"
            in 51..5000 -> "Normal"
            in 5001..25000 -> "cerah"
            else -> "cerah bangett"
        }
    }

    override fun onResume() {
        super.onResume()

        btnStart.isEnabled = true
        btnStop.isEnabled = false
    }

}