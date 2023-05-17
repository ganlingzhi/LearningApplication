package com.example.baselib.structure.base.view

import android.os.Bundle
import com.example.baselib.structure.base.IBaseView

open class BaseSimpleActivity : BaseActivity(), IBaseView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInit()
        initInternal()
    }

    protected open fun onInit() {

    }

    protected open fun initInternal() {
        buildView()
        setListener()
        processLogic()
    }

}