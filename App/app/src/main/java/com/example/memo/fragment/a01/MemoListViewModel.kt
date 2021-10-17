package com.example.memo.fragment.a01

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.memo.core.model.Memo
import com.example.memo.core.repository.memo.contract.MemoRepository
import com.example.memo.core.repository.memo.contract.MemoRepositoryInputRead
import com.example.memo.fragment.BaseFragmentViewModel
import com.example.memo.item.MemoItem
import com.xwray.groupie.Section
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MemoListViewModel(
    savedStateHandle: SavedStateHandle,
    private val memoRepository: MemoRepository,
) : BaseFragmentViewModel() {
    val searchText: Flow<String>

    val sections: Flow<List<Section>>

    private val _memos = MutableStateFlow<List<Memo>>(emptyList())

    private val _state = savedStateHandle.getStateFlow(MemoListState.initialValue)

    init {
        searchText = _state
            .map { it.searchText }

        sections = _memos.map { getSections(it) }

        viewModelScope.launch {
            _state.flatMapLatest { state ->
                val searchText = state.searchText
                if (searchText.isEmpty()) {
                    memoRepository.read(MemoRepositoryInputRead.All)
                } else {
                    memoRepository.read(MemoRepositoryInputRead.Contains(searchText))
                }
            }.collect {
                _memos.value = it
            }
        }
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

    fun setOnItemClickListener(index: Int) {
        viewModelScope.launch {
            transition(MemoListFragmentDirections.actionA01ToA02())
        }
    }

    companion object {
        private fun getSections(memos: List<Memo>): List<Section> {
            val memoSection = Section()
            val memoItems = memos.map { MemoItem(it.id.value, it.body) }
            memoSection.addAll(memoItems)
            return listOf(memoSection)
        }
    }
}
