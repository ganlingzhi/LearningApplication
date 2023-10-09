package com.example.learningapplication.main.view

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration


class CustomItemDecoration(// 可见宽度的阈值，这里设为0.85表示85%
    private val percentageThreshold: Float, // item之间的间距，单位为像素
    private val itemSpacing: Int, // 对RecyclerView的引用
    private val recyclerView: RecyclerView
) :
    ItemDecoration() {
    init {
        setupScrollListener() // 设置滚动监听器
    }

    private val set: HashSet<Int> = hashSetOf()


    private fun setupScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                checkExposure() // 实时判断并曝光满足条件的item
            }
        })
    }

    private fun checkExposure() {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager) {
            val first = layoutManager.findFirstVisibleItemPosition()
            val last = layoutManager.findLastVisibleItemPosition()
            if (layoutManager.orientation == LinearLayoutManager.HORIZONTAL) {
                for (i in first .. last) {
                    val view = layoutManager.getChildAt(i) ?: return
                    // 减去左右边距的影响，使得计算可见宽度时不考虑Item之间的间距
                    val itemWidth = view.width
                    val screenWidth = view.resources.displayMetrics.widthPixels
                    val rect = Rect()
                    view.getGlobalVisibleRect(rect)
                    // 计算item的可见宽度
                    val visibleItemWidth =
                        Math.min(rect.right, screenWidth)- Math.max(rect.left, 0)


                    // 计算item的可见宽度相对于Item宽度的比例
                    val visibleWidthPercentage = visibleItemWidth.toFloat() / itemWidth

                    // 根据阈值判断是否执行操作
                    if (visibleWidthPercentage > percentageThreshold && !set.contains(recyclerView.getChildAdapterPosition(view))) {
                        set.add(recyclerView.getChildAdapterPosition(view))
                        // 在这里执行你的操作，例如：
                        // view.setBackgroundColor(Color.RED);
                        // 或者调用某个方法，执行其他操作

                        // 打印当前曝光的item的position
                        Log.d(
                            "tag85",
                            "Exposed item position: ${recyclerView.getChildAdapterPosition(view)}"
                        )
                    }
                }
            }
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        // 保留添加间距的逻辑
        outRect.left = itemSpacing
        outRect.right = itemSpacing
    }
}

