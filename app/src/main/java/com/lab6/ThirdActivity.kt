package com.lab6

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {
  private lateinit var graphView: CustomGraphView
  private lateinit var noDataView: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_third)

    graphView = findViewById(R.id.graph_view)
    noDataView = findViewById(R.id.no_data_text)
  }

  override fun onResume() {
    super.onResume()
    // Refresh the displayed data
    updateData()
  }

  private fun updateData() {
    val points = DataManager.readPoints(this)
    if (points != null && points.isNotEmpty()) {
      graphView.visibility = View.VISIBLE
      noDataView.visibility = View.GONE
      graphView.setPoints(points)
    } else {
      graphView.visibility = View.GONE
      noDataView.visibility = View.VISIBLE
      noDataView.text = "Дані відсутні!"
    }
  }
}
