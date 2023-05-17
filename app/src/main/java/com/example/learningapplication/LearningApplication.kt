package com.example.learningapplication

import android.app.Application
import com.learning.app.commonlib.ability.Appkit

class LearningApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Appkit.initContext(this.applicationContext)
    }
}