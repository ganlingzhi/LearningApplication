package com.example.baselib.structure.mvvm.entity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher

class LaunchActivityResultInfo(
    val launcher: ActivityResultLauncher<Intent>,
    val clazz: Class<out Activity>,
    val bundle: Bundle? = null,
    val map: Map<String, *>? = null
) {
    companion object {
        private const val seralVersionUID = -15266L
    }
}