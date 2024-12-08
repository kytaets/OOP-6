package com.lab6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_third)

    // Отримуємо точки з Intent
    val points = intent.getSerializableExtra("points") as? ArrayList<Pair<Int, Int>>

    // Відображення графіка
    val graphView = findViewById<CustomGraphView>(R.id.graph_view)
    if (points != null) {
      graphView.setPoints(points)
    }
  }
}
