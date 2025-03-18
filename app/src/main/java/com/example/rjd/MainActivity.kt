package com.example.rjd

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val etDestination = findViewById<EditText>(R.id.etDestination)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        btnSearch.setOnClickListener {
            val destination = etDestination.text.toString().trim()
            val filteredTrains = trainList.filter { it.destination.equals(destination, ignoreCase = true) }

            if (filteredTrains.isEmpty()) {
                tvResult.text = "Поездов в '$destination' не найдено."
            } else {
                val result = StringBuilder()
                for (train in filteredTrains) {
                    result.append("Время: ${train.departureTime}, Свободных мест: ${train.freeSeats}\n")
                }
                tvResult.text = result.toString()
            }
    }}
}

private val trainList = listOf(
    Train("Moscow", "08:30", 50),
    Train("Санкт-Петербург", "12:15", 20),
    Train("Москва", "18:45", 10),
    Train("Казань", "09:00", 35)
)