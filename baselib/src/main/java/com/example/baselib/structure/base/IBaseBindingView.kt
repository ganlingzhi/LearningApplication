package com.example.baselib.structure.base

import android.view.LayoutInflater
import android.view.ViewGroup

interface IBaseBindingView<V> {
    fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): V?
}