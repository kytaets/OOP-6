package com.lab6

import android.content.Context
import java.io.File

object DataManager {
  private const val FILE_NAME = "points_data.txt"

  // Write points to the file
  fun writePoints(context: Context, points: List<Pair<Int, Int>>) {
    val file = File(context.filesDir, FILE_NAME)
    file.printWriter().use { writer ->
      points.forEach { (x, y) ->
        writer.println("$x,$y")
      }
    }
  }

  // Read points from the file
  fun readPoints(context: Context): List<Pair<Int, Int>>? {
    val file = File(context.filesDir, FILE_NAME)
    if (!file.exists()) return null

    return file.readLines().mapNotNull { line ->
      val coords = line.split(",")
      if (coords.size == 2) {
        val x = coords[0].toIntOrNull()
        val y = coords[1].toIntOrNull()
        if (x != null && y != null) Pair(x, y) else null
      } else null
    }
  }

  // Delete the file
  fun clearData(context: Context) {
    val file = File(context.filesDir, FILE_NAME)
    if (file.exists()) {
      file.delete()
    }
  }
}
