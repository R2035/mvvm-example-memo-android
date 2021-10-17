package com.example.memo.fragment.a02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import com.example.memo.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_editing_memo, menu)
    }
}
