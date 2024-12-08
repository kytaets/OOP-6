package com.lab6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
  private lateinit var resultView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second)

    resultView = findViewById(R.id.result_text)
    val nextButton = findViewById<Button>(R.id.next_button)

    nextButton.setOnClickListener {
      startActivity(Intent(this, ThirdActivity::class.java))
    }
  }

  override fun onResume() {
    super.onResume()
    // Refresh the displayed data
    updateData()
  }

  private fun updateData() {
    val points = DataManager.readPoints(this)
    if (points != null && points.isNotEmpty()) {
      resultView.text = points.joinToString("\n") { "(${it.first}, ${it.second})" }
    } else {
      resultView.text = "Дані відсутні!"
    }
  }
}
