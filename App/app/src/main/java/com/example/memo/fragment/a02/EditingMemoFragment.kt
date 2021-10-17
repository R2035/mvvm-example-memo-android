package com.example.memo.fragment.a02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.memo.R
import com.example.memo.databinding.FragmentEditingMemoBinding
import com.example.memo.fragment.BaseFragment
import kotlinx.coroutines.flow.collect
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

        lifecycleScope.launchWhenStarted {
            viewModel.body.collect {
                if (requireBinding().editingMemoEditText.text.toString() != it) {
                    requireBinding().editingMemoEditText.setText(it)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireBinding().editingMemoEditText.doAfterTextChanged {
            viewModel.bodyEditTextAfterTextChanged(it.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_editing_memo, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.editing_memo_menu_item_save -> {
                viewModel.onSaveOptionsItemSelected()
                true
            }
            R.id.memo_list_menu_item_delete -> {
                viewModel.onDeleteOptionsItemSelected()
                true
            }
            else -> {
                false
            }
        }
    }
}
