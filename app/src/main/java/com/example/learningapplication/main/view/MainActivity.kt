package com.example.learningapplication.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.appui.utils.DensityUtils
import com.example.baselib.structure.base.view.BaseActivity
import com.example.learningapplication.R
import com.learning.app.commonlib.utils.ValueUtils
import com.example.learningapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object{
        const val TAG_N = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buildView()
        setListener()

        Log.d(TAG_N, "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG_N,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG_N,"onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG_N,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG_N, "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG_N,"onDestroy")
    }

    private fun buildView() {
        binding.tvShowBottomSheet.background =
            ValueUtils.getDrawableById(R.drawable.bg_corner_6_purple)
        binding.tvShowBottomSheet.setTextColor(ValueUtils.getColorById(R.color.white))
        val adapter = SimpleAdapter()
        binding.rvList.adapter = adapter
        val list = arrayListOf("0", "1", "2", "3", "4", "5", "6", "7")
        binding.rvList.addItemDecoration(
            CustomItemDecoration(
                0.85f,
                DensityUtils.dp2px(ac, 10f),
                binding.rvList
            )
        )
        adapter.setData(list)
    }

    private fun setListener() {
        binding.tvShowBottomSheet.setOnClickListener {
            startActivity(Intent(this, BaseBindingTestActivity::class.java))
        }
    }

    private fun processLogic() {

    }
}