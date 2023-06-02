package com.example.appui.utils

import android.content.Context
import com.learning.app.commonlib.ability.Appkit

class DensityUtils {
    companion object {
        fun dp2px(context: Context, dpValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        fun Float.dp2px(context: Context): Int {
            return dp2px(context, this)
        }
    }
}