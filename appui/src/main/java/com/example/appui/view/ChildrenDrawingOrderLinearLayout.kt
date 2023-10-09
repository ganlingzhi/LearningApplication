package com.example.appui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat

class ChildrenDrawingOrderLinearLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {
    init {
        isChildrenDrawingOrderEnabled = true
    }

    override fun getChildDrawingOrder(childCount: Int, drawingPosition: Int): Int {
        return if (childCount >= 2) {
            super.getChildDrawingOrder(childCount, childCount - 1 - drawingPosition)
        } else {
            super.getChildDrawingOrder(childCount, drawingPosition)
        }
    }

}