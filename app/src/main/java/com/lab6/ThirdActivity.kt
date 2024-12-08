package com.lab6

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_third)

    // Отримання даних із Intent
    val points = intent.getSerializableExtra("points") as? ArrayList<Pair<Int, Int>>

    // Відображення у TextView
    val resultView = findViewById<TextView>(R.id.result_text)
    if (points != null) {
      val pointsText = points.joinToString("\n") { "(${it.first}, ${it.second})" }
      resultView.text = "Передані точки:\n$pointsText"
    } else {
      resultView.text = "Дані відсутні!"
    }
  }
}
