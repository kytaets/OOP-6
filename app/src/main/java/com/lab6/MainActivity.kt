package com.lab6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Поля вводу
    val nPointInput = findViewById<EditText>(R.id.input_nPoint)
    val xMinInput = findViewById<EditText>(R.id.input_xMin)
    val xMaxInput = findViewById<EditText>(R.id.input_xMax)
    val yMinInput = findViewById<EditText>(R.id.input_yMin)
    val yMaxInput = findViewById<EditText>(R.id.input_yMax)

    // Кнопка для генерації та передачі
    val generateButton = findViewById<Button>(R.id.generate_button)
    generateButton.setOnClickListener {
      // Зчитуємо значення
      val nPoint = nPointInput.text.toString().toIntOrNull() ?: 0
      val xMin = xMinInput.text.toString().toIntOrNull() ?: 0
      val xMax = xMaxInput.text.toString().toIntOrNull() ?: 0
      val yMin = yMinInput.text.toString().toIntOrNull() ?: 0
      val yMax = yMaxInput.text.toString().toIntOrNull() ?: 0

      // Перевірка діапазонів
      if (nPoint > 0 && xMin < xMax && yMin < yMax) {
        // Генерація випадкових пар (x, y)
        val points = List(nPoint) {
          Pair(Random.nextInt(xMin, xMax + 1), Random.nextInt(yMin, yMax + 1))
        }

        // Передача даних у SecondActivity
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("points", ArrayList(points)) // Передаємо як ArrayList
        startActivity(intent)
      } else {
        // Помилка, якщо дані введено некоректно
        nPointInput.error = "Перевірте введені значення"
      }
    }
  }
}
