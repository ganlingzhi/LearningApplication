package com.learning.app.commonlib.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlin.math.roundToInt

class DisplayUtils {

    companion object {
        fun decodeSampledBitmapFromResource(
            res: Resources,
            resId: Int,
            reqWidth: Int,
            reqHeight: Int
        ): Bitmap {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeResource(res, resId, options)
            var inSampleSize = 1
            val height = options.outHeight
            val width = options.outWidth
            if (height > reqHeight || width > reqWidth) {
                val heightRatio = (height.toFloat() / reqHeight.toFloat()).roundToInt()
                val widthRatio = (width.toFloat() / reqWidth.toFloat()).roundToInt()
                inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
            }
            options.inJustDecodeBounds = false
            options.inSampleSize = inSampleSize
            return BitmapFactory.decodeResource(res, resId, options)
        }
    }

}