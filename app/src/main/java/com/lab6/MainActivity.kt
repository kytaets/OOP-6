package com.lab6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

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

    // Кнопка для переходу до SecondActivity
    val goToSecondButton = findViewById<Button>(R.id.go_to_second)
    goToSecondButton.setOnClickListener {
      val intent = Intent(this, SecondActivity::class.java)

      // Передача даних через Intent
      intent.putExtra("nPoint", nPointInput.text.toString())
      intent.putExtra("xMin", xMinInput.text.toString())
      intent.putExtra("xMax", xMaxInput.text.toString())
      intent.putExtra("yMin", yMinInput.text.toString())
      intent.putExtra("yMax", yMaxInput.text.toString())

      startActivity(intent)
    }
  }
}
