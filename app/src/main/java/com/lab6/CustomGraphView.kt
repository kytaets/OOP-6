package com.lab6

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomGraphView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
  private val axisPaint = Paint().apply {
    color = Color.BLACK
    strokeWidth = 5f
    style = Paint.Style.STROKE
  }

  private val gridPaint = Paint().apply {
    color = Color.LTGRAY
    strokeWidth = 2f
    style = Paint.Style.STROKE
  }

  private val graphPaint = Paint().apply {
    color = Color.BLUE
    strokeWidth = 5f
    style = Paint.Style.STROKE
  }

  private val pointPaint = Paint().apply {
    color = Color.RED
    style = Paint.Style.FILL
  }

  private val textPaint = Paint().apply {
    color = Color.BLACK
    textSize = 30f
  }

  private var points: List<Pair<Int, Int>> = emptyList()

  fun setPoints(points: List<Pair<Int, Int>>) {
    this.points = points.sortedBy { it.first }
    invalidate()
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    val width = width
    val height = height
    val padding = 50

    canvas.drawLine(padding.toFloat(), (height - padding).toFloat(), (width - padding).toFloat(), (height - padding).toFloat(), axisPaint)
    canvas.drawLine(padding.toFloat(), (height - padding).toFloat(), padding.toFloat(), padding.toFloat(), axisPaint)

    canvas.drawText("X", (width - padding).toFloat() + 30, (height - padding).toFloat() + 40, textPaint)
    canvas.drawText("Y", padding.toFloat() - 40, padding.toFloat() - 20, textPaint)

    if (points.isEmpty()) return

    val minX = points.minOf { it.first }
    val maxX = points.maxOf { it.first }
    val minY = points.minOf { it.second }
    val maxY = points.maxOf { it.second }

    val xScale = (width - 2 * padding).toFloat() / (maxX - minX)
    val yScale = (height - 2 * padding).toFloat() / (maxY - minY)

    drawGrid(canvas, minX, maxX, minY, maxY, xScale, yScale, padding)

    val canvasPoints = points.map { point ->
      val x = padding + (point.first - minX) * xScale
      val y = height - padding - (point.second - minY) * yScale
      Pair(x, y)
    }

    for (i in 0 until canvasPoints.size - 1) {
      val (x1, y1) = canvasPoints[i]
      val (x2, y2) = canvasPoints[i + 1]
      canvas.drawLine(x1, y1, x2, y2, graphPaint)
    }

    canvasPoints.forEach { (x, y) ->
      canvas.drawCircle(x, y, 8f, pointPaint)
    }
  }

  private fun drawGrid(canvas: Canvas, minX: Int, maxX: Int, minY: Int, maxY: Int, xScale: Float, yScale: Float, padding: Int) {
    for (x in minX..maxX step 1) {
      val xPos = padding + (x - minX) * xScale
      canvas.drawLine(xPos, padding.toFloat(), xPos, (height - padding).toFloat(), gridPaint)
      canvas.drawText(x.toString(), xPos - 10, (height - padding + 40).toFloat(), textPaint)
    }

    for (y in minY..maxY step 1) {
      val yPos = height - padding - (y - minY) * yScale
      canvas.drawLine(padding.toFloat(), yPos, (width - padding).toFloat(), yPos, gridPaint)
      canvas.drawText(y.toString(), padding.toFloat() - 40, yPos + 10, textPaint)
    }
  }
}
