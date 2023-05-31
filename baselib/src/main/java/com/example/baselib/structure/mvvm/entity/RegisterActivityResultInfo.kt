package com.example.baselib.structure.mvvm.entity

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher

class RegisterActivityResultInfo(
    var callback: ActivityResultCallback<ActivityResult>,
    var block: (ActivityResultLauncher<Intent>) -> Unit
) : java.io.Serializable {
    companion object {
        private const val serialVersionUID = -74L
    }
}