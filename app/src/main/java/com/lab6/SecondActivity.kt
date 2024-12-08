package com.lab6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second)

    // Отримання даних із Intent
    val points = intent.getSerializableExtra("points") as? ArrayList<Pair<Int, Int>>

    // Відображення у TextView
    val resultView = findViewById<TextView>(R.id.result_text)
    if (points != null) {
      val pointsText = points.joinToString("\n") { "(${it.first}, ${it.second})" }
      resultView.text = "Згенеровані точки:\n$pointsText"
    } else {
      resultView.text = "Дані відсутні!"
    }

    // Кнопка для переходу на третє вікно
    val nextButton = findViewById<Button>(R.id.next_button)
    nextButton.setOnClickListener {
      val intent = Intent(this, ThirdActivity::class.java)
      intent.putExtra("points", points) // Передаємо точки далі
      startActivity(intent)
    }
  }
}
