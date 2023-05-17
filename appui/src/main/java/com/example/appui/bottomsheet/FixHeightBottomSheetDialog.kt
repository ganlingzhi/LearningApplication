package com.example.appui.bottomsheet

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.lang.ref.WeakReference

class FixHeightBottomSheetDialog(
    context: Context, theme: Int,
    private val peekHeight: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
    private val maxHeight: Int = ViewGroup.LayoutParams.MATCH_PARENT,
    private val wrapHeight: Boolean = true,
    private val expand: Boolean = false
) : BottomSheetDialog(context, theme) {

    private var bottomSheet: FrameLayout? = null

    private var weakReferenceCancelListener: DialogInterface.OnCancelListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPeekHeight()
        setMaxHeight()
    }

    override fun onStart() {
        super.onStart()
        bottomSheet?.let {
            if (!wrapHeight) {
                it.layoutParams.height = if (!isFixed()) {
                    peekHeight
                } else {
                    maxHeight
                }
            }
            it.post {
                it.setBackgroundResource(android.R.color.transparent)
            }
        }
        if (!isFixed() && expand) {
            getBottomSheetBehavior()?.state = BottomSheetBehavior.STATE_EXPANDED
        }
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

    private fun isFixed() = peekHeight == maxHeight

    class WrapperCancelListener(delegate: DialogInterface.OnCancelListener?) :
        DialogInterface.OnCancelListener {
        private var weakRef = WeakReference(delegate)
        override fun onCancel(dialog: DialogInterface?) {
            weakRef.get()?.onCancel(dialog)
        }

    }

    override fun setOnCancelListener(listener: DialogInterface.OnCancelListener?) {
        weakReferenceCancelListener = listener
        super.setOnCancelListener(WrapperCancelListener(listener))
    }

}