package com.example.learningapplication.appui

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog

class FixHeightBottomSheetDialog(
    context: Context, theme: Int
) : BottomSheetDialog(context, theme) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPeekHeight()
    }
    private fun setPeekHeight() {
        
    }
}