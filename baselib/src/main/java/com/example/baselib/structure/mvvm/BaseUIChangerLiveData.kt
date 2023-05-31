package com.example.baselib.structure.mvvm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.baselib.structure.mvvm.entity.LaunchActivityResultInfo
import com.example.baselib.structure.mvvm.entity.RegisterActivityResultInfo

open class BaseUIChangerLiveData {
    init {
        initStartActivityForResultLiveData()
        initStartAndFinishLiveData()
    }

    /**
     * 关闭页面
     */
    var finishPageLiveData: SingleLiveData<Any?>? = null
    var finishPageWithResultLiveData: SingleLiveData<Pair<Int, Intent?>?>? = null
    var finishPageDelayLiveData: SingleLiveData<Pair<Long?, String?>?>? = null

    /**
     * 打开页面
     */
    var startActivityByNameLiveData: SingleLiveData<Pair<String, Bundle?>>? = null
    var startActivityLiveData: SingleLiveData<Class<out Activity>>? = null
    var starActivityClearTaskLiveData: SingleLiveData<Class<out Activity>>? = null
    var startActivityWithMapLiveData: SingleLiveData<Class<out Activity>>? = null
    var startActivityWithBundleLiveData: SingleLiveData<Class<out Activity>>? = null

    /**
     * 打开页面 - 待返回结果
     */
    var registerForResultLiveData: SingleLiveData<RegisterActivityResultInfo>? = null
    var startActivityForResultLiveData: SingleLiveData<LaunchActivityResultInfo>? = null
    var startActivityForResultWithMapLiveData: SingleLiveData<LaunchActivityResultInfo>? = null
    var startActivityForResultWithBundleLiveData: SingleLiveData<LaunchActivityResultInfo>? = null

    private fun initStartActivityForResultLiveData() {
        registerForResultLiveData = SingleLiveData()
        startActivityForResultLiveData = SingleLiveData()
        startActivityForResultWithMapLiveData = SingleLiveData()
        startActivityForResultWithBundleLiveData = SingleLiveData()
    }

    private fun initStartAndFinishLiveData() {
        startActivityByNameLiveData = SingleLiveData()
        startActivityLiveData = SingleLiveData()
        starActivityClearTaskLiveData = SingleLiveData()
        startActivityWithMapLiveData = SingleLiveData()
        startActivityWithBundleLiveData = SingleLiveData()

        finishPageLiveData = SingleLiveData()
        finishPageDelayLiveData = SingleLiveData()
        finishPageWithResultLiveData = SingleLiveData()
    }
}