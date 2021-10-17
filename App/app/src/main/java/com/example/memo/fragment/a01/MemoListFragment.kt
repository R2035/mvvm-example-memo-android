package com.example.memo.fragment.a01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.example.memo.R
import com.example.memo.databinding.FragmentMemoListBinding
import com.example.memo.fragment.BaseFragment
import org.koin.androidx.viewmodel.ext.android.stateViewModel

/**
 * A01 メモ一覧画面
 */
class MemoListFragment : BaseFragment<MemoListFragmentViewModel, FragmentMemoListBinding>() {
    override val viewModel: MemoListFragmentViewModel by stateViewModel()

    override fun inflate(inflater: LayoutInflater, root: ViewGroup?, attachToParent: Boolean): FragmentMemoListBinding {
        return FragmentMemoListBinding.inflate(inflater, root, attachToParent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_memo_list, menu)
        val searchView = menu.findItem(R.id.memo_list_menu_item_search).actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.onSearchViewQueryTextSubmit(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.onSearchViewQueryTextChange(newText)
                return true
            }
        })

        viewModel.searchText.observe(viewLifecycleOwner) {
            searchView.setQuery(it, false)
        }
    }
}
