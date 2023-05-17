package com.example.baselib.structure.base.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity : AppCompatActivity() {
    @JvmField
    val TAG = javaClass.simpleName

    val taskTag: Any
        get() = "${this.javaClass.name}:${hashCode()}"

    lateinit var mContext: Context

    lateinit var ac: BaseActivity

    /**
     * Activity当前是否在前台
     */
    private var mIsForeground = false

    protected open var statusBarDarkMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeSetContent()
        setContentView()
        setStatusBar()
        mContext = this
        ac = this
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

    protected open fun beforeSetContent() {}

    protected open fun setContentView() {
        if (getLayoutResId() > 0) {
            setContentView(getLayoutResId())
        }
    }

    protected open fun getLayoutResId(): Int {
        return -1
    }

    fun isForeground() = mIsForeground

    override fun onPause() {
        super.onPause()
        mIsForeground = false
    }

    override fun onResume() {
        super.onResume()
        mIsForeground = true
    }
}