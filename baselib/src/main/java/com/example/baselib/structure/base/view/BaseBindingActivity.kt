package com.example.baselib.structure.base.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.baselib.structure.base.IBaseBindingView

class BaseBindingActivity<V : ViewBinding> : BaseSimpleActivity(), IBaseBindingView<V> {
    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): V? {
        return null
    }

}