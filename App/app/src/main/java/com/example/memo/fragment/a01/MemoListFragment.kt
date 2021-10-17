package com.example.memo.fragment.a01

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.R
import com.example.memo.databinding.FragmentMemoListBinding
import com.example.memo.fragment.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A01 メモ一覧画面
 */
class MemoListFragment : BaseFragment<MemoListViewModel, FragmentMemoListBinding>() {
    override val viewModel: MemoListViewModel by viewModel()

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun inflate(inflater: LayoutInflater, root: ViewGroup?, attachToParent: Boolean): FragmentMemoListBinding {
        return FragmentMemoListBinding.inflate(inflater, root, attachToParent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        lifecycleScope.launchWhenStarted {
            viewModel.sections.collect {
                groupAdapter.update(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), linearLayoutManager.orientation)
        requireBinding().memoListRecyclerView.adapter = groupAdapter
        requireBinding().memoListRecyclerView.layoutManager = linearLayoutManager
        requireBinding().memoListRecyclerView.addItemDecoration(dividerItemDecoration)

        groupAdapter.setOnItemClickListener { item, _ ->
            val index = groupAdapter.getAdapterPosition(item)
            viewModel.setOnItemClickListener(index)
        }
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

        lifecycleScope.launch {
            viewModel.searchText.collect {
                searchView.setQuery(it, false)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.memo_list_menu_item_add -> {
                viewModel.onAddOptionsItemSelected()
                true
            }
            else -> {
                false
            }
        }
    }
}
