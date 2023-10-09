package com.example.learningapplication.main

import android.graphics.Rect
import android.view.View


object ViewUtils {
    fun getVisibleWidth(view: View?): Int {
        view?.let {
            val rect = Rect()
            val isVisible = view.getGlobalVisibleRect(rect)
            if (isVisible) {
                val screenWidth = view.resources.displayMetrics.widthPixels
                val left = Math.max(rect.left, 0)
                val right = Math.min(rect.right, screenWidth)
                return right - left
            }
            return 0
        }
        return 0
    }
}
