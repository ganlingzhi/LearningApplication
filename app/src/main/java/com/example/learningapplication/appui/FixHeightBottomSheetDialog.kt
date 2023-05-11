package com.example.learningapplication.appui

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.learningapplication.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.math.max

class FixHeightBottomSheetDialog(
    context: Context, theme: Int,
    private val peekHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    private val maxHeight: Int = ViewGroup.LayoutParams.MATCH_PARENT
) : BottomSheetDialog(context, theme) {

    private var bottomSheet: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPeekHeight()
        setMaxHeight()
    }

    private fun setPeekHeight() {
        if (peekHeight <= 0) {
            return
        }
        getBottomSheetBehavior()?.peekHeight = peekHeight
    }

    private fun setMaxHeight() {
        if (maxHeight <= 0) {
            return
        }
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, maxHeight)
        window?.setGravity(Gravity.BOTTOM)
    }

    fun getBottomSheetBehavior(): BottomSheetBehavior<View>? {
        bottomSheet = window?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        return bottomSheet?.let {
            BottomSheetBehavior.from(it)
        }
    }
}