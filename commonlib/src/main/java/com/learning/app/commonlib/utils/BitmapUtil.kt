package com.learning.app.commonlib.utils
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache

object BitmapUtils {

    private const val TAG = "BitmapUtils"

    /**
     * 加载图片并压缩
     *
     * @param res 资源对象
     * @param resId 图片资源 ID
     * @param reqWidth 希望显示的宽度
     * @param reqHeight 希望显示的高度
     * @return 压缩后的 Bitmap 对象
     */
    fun decodeSampledBitmapFromResource(res: Resources, resId: Int, reqWidth: Int, reqHeight: Int): Bitmap {
        // 第一次解析将 inJustDecodeBounds 设为 true，获取图片大小
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
            val bitmap = BitmapFactory.decodeResource(res, resId, this)
            // 计算 inSampleSize 的值
            inSampleSize = calculateInSampleSize(this, reqWidth, reqHeight)
            // 设置 inPreferredConfig，减少内存占用
            inPreferredConfig = if (bitmap.hasAlpha()) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
            // 设置 inMutable，允许修改 Bitmap 对象
            inMutable = true
        }
        Log.d(TAG, "inSampleSize: ${options.inSampleSize}")
        // 使用计算出的 inSampleSize 压缩图片，并返回 Bitmap 对象
        return BitmapFactory.decodeResource(res, resId, options)
    }

    /**
     * 计算 inSampleSize 的值
     *
     * @param options BitmapFactory.Options 对象
     * @param reqWidth 希望显示的宽度
     * @param reqHeight 希望显示的高度
     * @return inSampleSize 的值
     */
    private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // 获取图片的原始宽度和高度
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1
        // 如果原始宽度或高度大于期望宽度或高度，则计算 inSampleSize 的值
        if (height > reqHeight || width > reqWidth) {
            val halfHeight = height / 2
            val halfWidth = width / 2
            // 计算 inSampleSize 的初始值
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
            // 如果 inSampleSize 仍然小于 1，则将其设为 1
            if (inSampleSize < 1) {
                inSampleSize = 1
            }
        }
        return inSampleSize
    }

    /**
     * 使用 LruCache 缓存 Bitmap 对象
     */
    private val bitmapCache: LruCache<String, Bitmap> by lazy {
        val cacheSize = (Runtime.getRuntime().maxMemory() / 1024).toInt() / 8
        LruCache<String, Bitmap>(cacheSize)
    }

    /**
     * 从缓存中获取 Bitmap 对象，如果缓存中不存在，则加载图片并存入缓存
     *
     * @param res 资源对象
     * @param resId 图片资源 ID
     * @param reqWidth 希望显示的宽度
     * @param reqHeight 希望显示的高度
     * @return 压缩后的 Bitmap 对象
     */
    fun getBitmapFromCache(res: Resources, resId: Int, reqWidth: Int, reqHeight: Int): Bitmap {
        val key = "$resId-$reqWidth-$reqHeight"
        var bitmap = bitmapCache.get(key)
        if (bitmap != null) {
            Log.d(TAG, "get bitmap from cache: $key")
            return bitmap
        }
        bitmap = decodeSampledBitmapFromResource(res, resId, reqWidth, reqHeight)
        bitmapCache.put(key, bitmap)
        Log.d(TAG, "put bitmap into cache: $key")
        return bitmap
    }
}
