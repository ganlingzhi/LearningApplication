package com.example.appui.bottomsheet


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import com.example.appui.utils.DensityUtils.Companion.dp2px

/**
 * 简历卡片中，某些TextView长度不定，根据返回的具体文本给定长度，实现UI所需的各种卡片状态
 */
class WrapChildrenViewGroup @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attr, defStyleAttr) {

    /**
     * 丈量所需的measureSpec（无限空间）
     */
    private val measureSpecWidth: Int by lazy {
        MeasureSpec.makeMeasureSpec(1 shl 30 - 1, MeasureSpec.AT_MOST)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        measureTypeOne(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = l
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (view.visibility != View.GONE) {
                val childWith = view.measuredWidth
                val childHeight = view.measuredHeight
                //开始摆放,第一个img需要居中和margin
                if (i == 0 && view is AppCompatImageView) {
                    view.layout(
                        left,
                        t + 3.5f.dp2px(context),
                        left + childWith,
                        t + 3.5f.dp2px(context) + childHeight
                    )
                    left += 4f.dp2px(context)
                } else {
                    view.layout(left, t, left + childWith, t + childHeight)
                }
                //把左边的锚定位置往右移
                left += childWith
            }
        }
    }

    private fun measureTypeOne(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //可用宽度
        val tWidth = MeasureSpec.getSize(widthMeasureSpec)
        if (childCount != 4) return
        //计算第一个和最后一个view所需的宽度
        val childFirst = getChildAt(0)
        val childLast = getChildAt(childCount - 1)
        measureChild(childFirst, widthMeasureSpec, heightMeasureSpec)
        measureChild(childLast, widthMeasureSpec, heightMeasureSpec)
        //总宽度要加上边距
        val widthTotalUsed = 4f.dp2px(context) + childFirst.measuredWidth + childLast.measuredWidth

        //剩余总宽度
        val remainingWidth = tWidth - widthTotalUsed

        val childSecond = getChildAt(1)
        val childThird = getChildAt(2)

        //第一个和最后一个不受影响
        measureChild(childSecond, measureSpecWidth, heightMeasureSpec)
        measureChild(childThird, measureSpecWidth, heightMeasureSpec)

        //剩下的两个TextView所需宽度
        val needTotalWidth = childSecond.measuredWidth + childThird.measuredWidth

        //两个TextView实际可获得的宽度
        var exactWidth2 = 0
        var exactWidth3 = 0
        //当剩余空间足够展示
        if (remainingWidth >= needTotalWidth) {
            exactWidth2 =
                MeasureSpec.makeMeasureSpec(childSecond.measuredWidth, MeasureSpec.EXACTLY)
            exactWidth3 = MeasureSpec.makeMeasureSpec(childThird.measuredWidth, MeasureSpec.EXACTLY)


        }
        //不够展示，根据不同的情况设置宽度
        else {
            if (childSecond.measuredWidth >= remainingWidth / 2 && childThird.measuredWidth >= remainingWidth / 2) {
                exactWidth2 = MeasureSpec.makeMeasureSpec(remainingWidth / 2, MeasureSpec.EXACTLY)
                exactWidth3 = MeasureSpec.makeMeasureSpec(remainingWidth / 2, MeasureSpec.EXACTLY)

            } else if (childSecond.measuredWidth <= remainingWidth / 2) {
                exactWidth2 =
                    MeasureSpec.makeMeasureSpec(childSecond.measuredWidth, MeasureSpec.EXACTLY)
                exactWidth3 = MeasureSpec.makeMeasureSpec(
                    remainingWidth - childSecond.measuredWidth,
                    MeasureSpec.EXACTLY
                )

            } else if (childThird.measuredWidth <= remainingWidth / 2) {
                exactWidth2 = MeasureSpec.makeMeasureSpec(
                    remainingWidth - childThird.measuredWidth,
                    MeasureSpec.EXACTLY
                )
                exactWidth3 =
                    MeasureSpec.makeMeasureSpec(childThird.measuredWidth, MeasureSpec.EXACTLY)
            }
        }
        measureChild(childSecond, exactWidth2, widthMeasureSpec)
        measureChild(childThird, exactWidth3, widthMeasureSpec)
    }
}