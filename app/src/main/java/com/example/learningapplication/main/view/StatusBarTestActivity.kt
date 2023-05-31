package com.example.learningapplication.main.view

import com.example.baselib.structure.base.view.BaseBindingActivity
import com.example.learningapplication.databinding.ActivityMainBinding
import com.jaeger.library.StatusBarUtil

class StatusBarTestActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun setStatusBar() {
        StatusBarUtil.setTransparent(this)
        StatusBarUtil.setLightMode(this)
    }
}
