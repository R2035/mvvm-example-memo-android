package com.example.memo.fragment.a01

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.memo.core.repository.memo.contract.MemoRepository
import com.example.memo.fragment.BaseFragmentViewModel
import kotlinx.coroutines.flow.map

class MemoListFragmentViewModel(
    savedStateHandle: SavedStateHandle,
    private val memoRepository: MemoRepository,
) : BaseFragmentViewModel() {
    val searchText: LiveData<String>

    private val _state = savedStateHandle.getStateFlow(MemoListState.initialValue)

    init {
        searchText = _state
            .map { it.searchText }
            .asLiveData(viewModelScope.coroutineContext)
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
}
