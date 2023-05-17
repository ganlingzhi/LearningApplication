package com.example.appui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.appui.R
import com.example.appui.databinding.FragmentNcbottomsheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NcBottomSheetDialog : BottomSheetDialogFragment(), IDialogPerformance {

    private var _biding: FragmentNcbottomsheetDialogBinding? = null
    private val mBinding get() = _biding!!

    /**
     * 内容承载fragment
     */
    private var content: IDialogContent? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _biding = FragmentNcbottomsheetDialogBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (content == null) {
            return
        }
        when (content) {
            is Fragment -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.fl_root, content as Fragment)
                    .commit()
            }
            else -> {
                throw RuntimeException("NcBottomSheetDialog content only supports fragment")
            }
        }
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (manager.isStateSaved) {
            return
        }
        super.show(manager, tag)
    }

    override fun showNow(manager: FragmentManager, tag: String?) {
        if (manager.isStateSaved) {
            return
        }
        manager.beginTransaction().remove(this).commitNowAllowingStateLoss()
        super.showNow(manager, tag)
    }

}