package com.example.baselib.structure.mvvm

import androidx.viewbinding.ViewBinding

interface IMVVMView<Binding:ViewBinding, VM:BaseViewModel<out IBaseModel>> {


    fun createViewModel(): VM

}