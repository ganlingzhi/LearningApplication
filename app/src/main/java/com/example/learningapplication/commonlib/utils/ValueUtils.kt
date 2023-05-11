package com.example.learningapplication.commonlib.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.example.learningapplication.commonlib.Appkit

object ValueUtils {
    fun getDrawableById(@DrawableRes id: Int, context: Context = Appkit.context): Drawable? {
        return ContextCompat.getDrawable(context, id)
    }
    fun getColorById(@ColorInt id: Int, context: Context = Appkit.context): Int {
        return ContextCompat.getColor(context, id)
    }
}