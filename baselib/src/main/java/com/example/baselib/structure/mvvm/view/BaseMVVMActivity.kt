package com.example.baselib.structure.mvvm.view

import androidx.viewbinding.ViewBinding
import com.example.baselib.structure.base.view.BaseBindingActivity
import com.example.baselib.structure.mvvm.BaseViewModel
import com.example.baselib.structure.mvvm.IMVVMView

abstract class BaseMVVMActivity<Binding : ViewBinding, VM : BaseViewModel> :
    BaseBindingActivity<Binding>(), IMVVMView<Binding,VM> {

}