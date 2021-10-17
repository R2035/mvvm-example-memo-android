package com.example.memo.fragment.a01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.memo.databinding.FragmentMemoListBinding
import com.example.memo.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A01 メモ一覧画面
 */
class MemoListFragment : BaseFragment<MemoListFragmentViewModel, FragmentMemoListBinding>() {
    override val viewModel: MemoListFragmentViewModel by viewModel()

    override fun inflate(inflater: LayoutInflater, root: ViewGroup?, attachToParent: Boolean): FragmentMemoListBinding {
        return FragmentMemoListBinding.inflate(inflater, root, attachToParent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
