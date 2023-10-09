package com.example.appui.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.LinearLayoutCompat

class ReOrderLinearLayout @JvmOverloads constructor(
    mContext: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(mContext, attrs) {
    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
        isChildrenDrawingOrderEnabled = true
    }
}