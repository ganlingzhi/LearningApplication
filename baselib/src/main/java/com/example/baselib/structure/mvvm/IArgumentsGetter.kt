package com.example.baselib.structure.mvvm

import android.content.Intent
import android.os.Bundle

interface IArgumentsGetter {
    /**
     * 传递 Intent，可以在这里获取
     */
    fun getArgumentsIntent(): Intent?

    /**
     * 传递 bundle，在这里可以获取
     */
    fun getArgumentsBundle(): Bundle?
}