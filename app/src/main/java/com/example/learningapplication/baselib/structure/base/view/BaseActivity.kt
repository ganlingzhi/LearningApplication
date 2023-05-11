package com.example.learningapplication.baselib.structure.base.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity : AppCompatActivity() {
    @JvmField
    val TAG = javaClass.simpleName

    protected open var statusBarDarkMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
    }

    private fun setStatusBar() {
        ImmersionBar.with(this)
            .transparentStatusBar()
            .statusBarDarkFont(statusBarDarkMode)
            .titleBar(getViewBelowStatusBar())
            .init()
    }

    protected open fun getViewBelowStatusBar(): View? {
        return null
    }
}