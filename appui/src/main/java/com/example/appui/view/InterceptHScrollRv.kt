package com.example.appui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import java.lang.Math.abs

class InterceptHScrollRv @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var isScrolling = false

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        when (e.actionMasked) {
            MotionEvent.ACTION_DOWN -> isScrolling = false
            MotionEvent.ACTION_MOVE -> {
                if (!isScrolling) {
                    val dx = abs(e.x - scrollX)
                    val dy = abs(e.y - scrollY)
                    isScrolling = dx > dy // 判断是否为水平滑动
                    if (isScrolling) {
                        // 取消RecyclerView的触摸事件
                        parent?.requestDisallowInterceptTouchEvent(true)
                        return super.onInterceptTouchEvent(e)
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> isScrolling = false
        }

        return super.onInterceptTouchEvent(e)
    }
}
