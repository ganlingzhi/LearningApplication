package com.example.baselib.structure.base.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.baselib.structure.base.IBaseBindingView
import com.learning.app.commonlib.utils.ReflectUtil

open class BaseBindingActivity<V : ViewBinding> : BaseSimpleActivity(), IBaseBindingView<V> {
    private var _binding: V? = null
    private val mBinding get() = _binding!!
    override fun setContentView() {
        initViewBinding(layoutInflater, null)?.let {
            _binding = it
        }

        if (_binding == null) {
            throw RuntimeException("you must use correct binding with BaseBindingActivity")
        }
        setContentView(mBinding.root)

    }

    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?): V? {
        val clz = ReflectUtil.getTargetTFromObjOp<V>(
            this,
            BaseBindingActivity::class.java,
            ViewBinding::class.java
        )
        var binding: V? = null
        clz?.let {
            for (method in clz.methods) {
                if ("inflate" == method.name && method.parameterTypes.size == 3) {
                    binding = method.invoke(clz, inflater, null, false) as V
                    break
                }
            }
        }
        return binding
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}