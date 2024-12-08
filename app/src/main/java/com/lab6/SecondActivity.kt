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

    // Отримання даних від MainActivity
    val nPoint = intent.getStringExtra("nPoint")
    val xMin = intent.getStringExtra("xMin")
    val xMax = intent.getStringExtra("xMax")
    val yMin = intent.getStringExtra("yMin")
    val yMax = intent.getStringExtra("yMax")

    // Відображення отриманих даних
    val displayText = findViewById<TextView>(R.id.display_text)
    displayText.text = "nPoint: $nPoint\nxMin: $xMin\nxMax: $xMax\nyMin: $yMin\nyMax: $yMax"

    // Кнопка для переходу до ThirdActivity
    val goToThirdButton = findViewById<Button>(R.id.go_to_third)
    goToThirdButton.setOnClickListener {
      val intent = Intent(this, ThirdActivity::class.java)

      // Передача тих самих даних до ThirdActivity
      intent.putExtra("nPoint", nPoint)
      intent.putExtra("xMin", xMin)
      intent.putExtra("xMax", xMax)
      intent.putExtra("yMin", yMin)
      intent.putExtra("yMax", yMax)

      startActivity(intent)
    }
  }
}
