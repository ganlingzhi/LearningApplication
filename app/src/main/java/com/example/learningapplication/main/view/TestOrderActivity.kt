package com.example.learningapplication.main.view

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.appui.R
import com.example.appui.databinding.LayoutOrderBinding
import com.example.appui.utils.DensityUtils.Companion.dp2px
import com.example.baselib.structure.base.view.BaseBindingActivity
import com.learning.app.commonlib.utils.ValueUtils

class TestOrderActivity : BaseBindingActivity<LayoutOrderBinding>() {
    override fun buildView() {
        super.buildView()
        for (i in 0..5) {
            val imageView = ImageView(this).apply {
                val layoutParams =
                    LinearLayoutCompat.LayoutParams(70f.dp2px(context), 20f.dp2px(context))
                this.layoutParams = layoutParams
                if (i > 0) {
                    (layoutParams as LinearLayout.LayoutParams).leftMargin = -(10f.dp2px(context))
                }
                z = (100 - i).toFloat()
            }
            mBinding.root.addView(imageView)
            imageView.setImageDrawable(ValueUtils.getDrawableById(R.drawable.goodstore))
        }
    }
}