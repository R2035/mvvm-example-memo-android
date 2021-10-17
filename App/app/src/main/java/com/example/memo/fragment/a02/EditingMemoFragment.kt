package com.example.memo.fragment.a02

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.memo.databinding.FragmentEditingMemoBinding
import com.example.memo.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.stateViewModel

/**
 * A02 メモ編集画面
 */
class EditingMemoFragment : BaseFragment<EditingMemoViewModel, FragmentEditingMemoBinding>() {
    override val viewModel: EditingMemoViewModel by stateViewModel()

    override fun inflate(inflater: LayoutInflater, root: ViewGroup?, attachToParent: Boolean): FragmentEditingMemoBinding {
        return FragmentEditingMemoBinding.inflate(inflater, root, attachToParent)
    }
}
