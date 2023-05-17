package com.learning.app.commonlib.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.learning.app.commonlib.ability.Appkit

object ValueUtils {
    fun getDrawableById(@DrawableRes id: Int, context: Context = Appkit.context): Drawable? {
        return ContextCompat.getDrawable(context, id)
    }

    fun getColorById(@ColorRes id: Int, context: Context = Appkit.context): Int {
        return ContextCompat.getColor(context, id)
    }
}