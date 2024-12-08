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

    val nPointInput = findViewById<EditText>(R.id.input_nPoint)
    val xMinInput = findViewById<EditText>(R.id.input_xMin)
    val xMaxInput = findViewById<EditText>(R.id.input_xMax)
    val yMinInput = findViewById<EditText>(R.id.input_yMin)
    val yMaxInput = findViewById<EditText>(R.id.input_yMax)
    val generateButton = findViewById<Button>(R.id.generate_button)
    val closeButton = findViewById<Button>(R.id.close_button)

    generateButton.setOnClickListener {
      val nPoint = nPointInput.text.toString().toIntOrNull() ?: 0
      val xMin = xMinInput.text.toString().toIntOrNull() ?: 0
      val xMax = xMaxInput.text.toString().toIntOrNull() ?: 0
      val yMin = yMinInput.text.toString().toIntOrNull() ?: 0
      val yMax = yMaxInput.text.toString().toIntOrNull() ?: 0

      if (nPoint > 0 && xMin < xMax && yMin < yMax) {
        val points = List(nPoint) {
          Pair(Random.nextInt(xMin, xMax + 1), Random.nextInt(yMin, yMax + 1))
        }

        DataManager.writePoints(this, points)

        startActivity(Intent(this, SecondActivity::class.java))
      } else {
        nPointInput.error = "Перевірте введені значення"
      }
    }

    closeButton.setOnClickListener {
      DataManager.clearData(this)
      finishAffinity()
    }
  }
}
