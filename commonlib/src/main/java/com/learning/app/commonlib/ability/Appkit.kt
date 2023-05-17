package com.learning.app.commonlib.ability

import android.content.Context

class Appkit {
    companion object {
        lateinit var context: Context
        fun initContext(ctx: Context) {
            context = ctx
        }
    }
}