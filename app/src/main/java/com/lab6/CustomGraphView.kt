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
    color = Color.RED  // Можна змінити колір точки
    style = Paint.Style.FILL  // Суцільне заповнення
  }

  private val textPaint = Paint().apply {
    color = Color.BLACK
    textSize = 30f
  }

  private var points: List<Pair<Int, Int>> = emptyList()

  fun setPoints(points: List<Pair<Int, Int>>) {
    this.points = points.sortedBy { it.first } // Сортуємо точки за зростанням x
    invalidate() // Оновлюємо графік
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    // Розміри вікна
    val width = width
    val height = height
    val padding = 50

    // Малюємо осі координат
    canvas.drawLine(padding.toFloat(), (height - padding).toFloat(), (width - padding).toFloat(), (height - padding).toFloat(), axisPaint) // X-axis
    canvas.drawLine(padding.toFloat(), (height - padding).toFloat(), padding.toFloat(), padding.toFloat(), axisPaint) // Y-axis

    // Підписи осей
    canvas.drawText("X", (width - padding).toFloat() + 30, (height - padding).toFloat() + 40, textPaint)  // Зміщуємо X правіше
    canvas.drawText("Y", padding.toFloat() - 40, padding.toFloat() - 20, textPaint)  // Піднімаємо Y вище

    if (points.isEmpty()) return

    // Знаходимо мінімальні та максимальні значення x і y
    val minX = points.minOf { it.first }
    val maxX = points.maxOf { it.first }
    val minY = points.minOf { it.second }
    val maxY = points.maxOf { it.second }

    // Масштаб
    val xScale = (width - 2 * padding).toFloat() / (maxX - minX)
    val yScale = (height - 2 * padding).toFloat() / (maxY - minY)

    // Малюємо сітку
    drawGrid(canvas, minX, maxX, minY, maxY, xScale, yScale, padding)

    // Перетворюємо точки на координати для Canvas
    val canvasPoints = points.map { point ->
      val x = padding + (point.first - minX) * xScale
      val y = height - padding - (point.second - minY) * yScale
      Pair(x, y)
    }

    // Малюємо графік
    for (i in 0 until canvasPoints.size - 1) {
      val (x1, y1) = canvasPoints[i]
      val (x2, y2) = canvasPoints[i + 1]
      canvas.drawLine(x1, y1, x2, y2, graphPaint)
    }

    // Малюємо суцільні точки
    canvasPoints.forEach { (x, y) ->
      canvas.drawCircle(x, y, 8f, pointPaint) // Малюємо суцільні червоні кола
    }
  }

  private fun drawGrid(canvas: Canvas, minX: Int, maxX: Int, minY: Int, maxY: Int, xScale: Float, yScale: Float, padding: Int) {
    // Малюємо вертикальні лінії сітки
    for (x in minX..maxX step 1) {
      val xPos = padding + (x - minX) * xScale
      canvas.drawLine(xPos, padding.toFloat(), xPos, (height - padding).toFloat(), gridPaint)
      canvas.drawText(x.toString(), xPos - 10, (height - padding + 40).toFloat(), textPaint)
    }

    // Малюємо горизонтальні лінії сітки
    for (y in minY..maxY step 1) {
      val yPos = height - padding - (y - minY) * yScale
      canvas.drawLine(padding.toFloat(), yPos, (width - padding).toFloat(), yPos, gridPaint)
      canvas.drawText(y.toString(), padding.toFloat() - 40, yPos + 10, textPaint)
    }
  }
}
