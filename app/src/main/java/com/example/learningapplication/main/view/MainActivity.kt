package com.example.learningapplication.main.view

import android.os.Bundle
import com.example.baselib.structure.base.view.BaseActivity
import com.example.learningapplication.R
import com.learning.app.commonlib.utils.ValueUtils
import com.example.learningapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildView()
        setListener()
    }

    private fun buildView() {
        binding.tvShowBottomSheet.background =
            ValueUtils.getDrawableById(R.drawable.bg_corner_6_purple)
        binding.tvShowBottomSheet.setTextColor(ValueUtils.getColorById(R.color.white))
    }

    private fun setListener() {
        binding.tvShowBottomSheet.setOnClickListener {

        }
    }

    private fun processLogic() {

    }
}