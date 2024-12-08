package com.lab6

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_third)

    // Отримання даних від SecondActivity
    val nPoint = intent.getStringExtra("nPoint")
    val xMin = intent.getStringExtra("xMin")
    val xMax = intent.getStringExtra("xMax")
    val yMin = intent.getStringExtra("yMin")
    val yMax = intent.getStringExtra("yMax")

    // Відображення отриманих даних
    val displayText = findViewById<TextView>(R.id.display_text)
    displayText.text = "Дані отримано:\n\nnPoint: $nPoint\nxMin: $xMin\nxMax: $xMax\nyMin: $yMin\nyMax: $yMax"
  }
}
