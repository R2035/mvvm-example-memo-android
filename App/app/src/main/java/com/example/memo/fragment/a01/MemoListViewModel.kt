package com.example.memo.fragment.a01

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.memo.core.repository.memo.contract.MemoRepository
import com.example.memo.fragment.BaseFragmentViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MemoListViewModel(
    savedStateHandle: SavedStateHandle,
    private val memoRepository: MemoRepository,
) : BaseFragmentViewModel() {
    val searchText: Flow<String>

    private val _state = savedStateHandle.getStateFlow(MemoListState.initialValue)

    init {
        searchText = _state
            .map { it.searchText }
    }

    fun onSearchViewQueryTextSubmit(query: String?) {
        _state.value = _state.value.copy(
            searchText = query ?: ""
        )
    }

    fun onSearchViewQueryTextChange(newText: String?) {
        _state.value = _state.value.copy(
            searchText = newText ?: ""
        )
    }

    fun onAddOptionsItemSelected() {
        viewModelScope.launch {
            transition(MemoListFragmentDirections.actionA01ToA02())
        }
    }
}
