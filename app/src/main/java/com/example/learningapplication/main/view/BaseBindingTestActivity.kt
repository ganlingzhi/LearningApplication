package com.example.learningapplication.main.view

import android.content.Intent
import com.example.baselib.structure.base.view.BaseBindingActivity
import com.example.learningapplication.databinding.ActivityMainBinding

class BaseBindingTestActivity : BaseBindingActivity<ActivityMainBinding>() {

    override fun buildView() {
        super.buildView()
        mBinding.tvShowBottomSheet.text = "下一个是BaseMVVMActivity"
    }

    override fun setListener() {
        super.setListener()
        mBinding.tvShowBottomSheet.setOnClickListener {
            startActivity(Intent(this,StatusBarTestActivity::class.java))
        }
    }
}