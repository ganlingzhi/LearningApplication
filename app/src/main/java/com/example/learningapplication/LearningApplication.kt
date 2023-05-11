package com.example.learningapplication

import android.app.Application
import com.example.learningapplication.commonlib.Appkit

class LearningApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Appkit.initContext(this.applicationContext)
    }
}