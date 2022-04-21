package com.codepalace.light_sensor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuUtama : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_utama)

        val btnSensorCahaya = findViewById<Button>(R.id.idbtn_sensorcahaya)

        btnSensorCahaya.setOnClickListener {
            val intente = Intent(this, MainActivity::class.java)
            startActivity(intente)
        }
    }
}