package com.example.appui.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.example.appui.R
import com.example.appui.utils.DensityUtils.Companion.dp2px
import com.learning.app.commonlib.ability.Appkit
import com.learning.app.commonlib.utils.ValueUtils

class NCAlertView @JvmOverloads constructor(
    mContext: Context, attrs: AttributeSet? = null
) : LinearLayout(mContext, attrs) {

    /**
     * 提示等级，用来控制色值
     */
    private var alertLevel: Int = ALERT_LEVEL_NORMAL

    /**
     * 提示文案颜色，会根据提示等级变化
     */
    var alertColor: Int = 0

    /**
     * 跟随提示右方drawable
     */
    var drawableEnd: Drawable? = null

    companion object {
        const val ALERT_LEVEL_NORMAL = 0
        const val ALERT_LEVEL_WARNING = 1
        const val ALERT_LEVEL_ERROR = 2
    }

    init {
        orientation = HORIZONTAL
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        setPadding(
            12f.dp2px(context),
            16f.dp2px(context),
            12f.dp2px(context),
            16f.dp2px(context)
        )
    }

    class NCAlertViewBuilder(private val context: Context) {
        private var alertView: NCAlertView? = null

        init {
            alertView = NCAlertView(context)
        }

        fun alertLevel(level: Int): NCAlertViewBuilder {
            alertView?.alertLevel = level
            return this
        }

        fun adjustColorWithLevel(): NCAlertViewBuilder {
            alertView?.alertColor = when (alertView?.alertLevel) {
                ALERT_LEVEL_NORMAL -> ContextCompat.getColor(context, R.color.green)
                ALERT_LEVEL_WARNING -> ContextCompat.getColor(context, R.color.yellow)
                else ->
                    ContextCompat.getColor(context, R.color.red)
            }
            return this
        }

        fun alertDrawableEnd(@DrawableRes drawable: Int): NCAlertViewBuilder {
            alertView?.drawableEnd = ValueUtils.getDrawableById(drawable)
            return this
        }
    }

}