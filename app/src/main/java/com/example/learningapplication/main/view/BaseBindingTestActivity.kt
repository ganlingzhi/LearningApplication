package com.example.learningapplication.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.baselib.structure.base.view.BaseBindingActivity
import com.example.learningapplication.databinding.ActivityMainBinding

class BaseBindingTestActivity : BaseBindingActivity<ActivityMainBinding>() {

    companion object {
        const val TAG_N = "BaseBindingTestActivity"
    }
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG_N, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_N, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_N, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG_N,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_N,"onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_N, "onDestroy")
    }
}
