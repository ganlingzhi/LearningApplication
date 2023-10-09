package com.example.appui.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class RightAlignedTextView : AppCompatTextView {
    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
    }

    override fun onDraw(canvas: Canvas) {
        // Get the text content of the TextView
        val text = text.toString()

        // Get the available width for drawing the text
        val availableWidth = width - paddingStart - paddingEnd

        // Create a Layout object to measure and draw the text
        val layout = layout
        if (layout == null) {
            super.onDraw(canvas)
            return
        }

        // Loop through each line of text and draw it from the right side
        for (line in 0 until layout.lineCount) {
            val lineStart = layout.getLineStart(line)
            val lineEnd = layout.getLineEnd(line)
            val lineText = text.substring(lineStart, lineEnd)

            // Measure the width of the line of text
            val lineWidth = layout.getLineWidth(line)

            // Calculate the x-coordinate for drawing the text
            val x = width - paddingEnd - lineWidth

            // Calculate the y-coordinate for drawing the text
            val y = (paddingTop + layout.getLineBaseline(line)).toFloat()

            // Draw the line of text
            canvas.drawText(lineText, x, y, paint)
        }
    }
}
