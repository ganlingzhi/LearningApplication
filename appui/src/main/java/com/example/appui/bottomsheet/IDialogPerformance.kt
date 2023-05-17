package com.example.appui.bottomsheet

import androidx.fragment.app.Fragment

interface IDialogPerformance {
    fun dismiss()
}

interface IDialogContent : IDialogPerformance {
    val current: Fragment?

    override fun dismiss() {
        current?.let {
            (it.parentFragment as? IDialogPerformance)?.dismiss()
        }
    }
}