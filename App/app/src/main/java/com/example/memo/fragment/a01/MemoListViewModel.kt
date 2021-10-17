package com.example.memo.fragment.a01

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
    private val memoRepository: MemoRepository,
) : BaseFragmentViewModel() {
    val searchText: Flow<String>

    val sections: Flow<List<Section>>

    private val _memos = MutableStateFlow<List<Memo>>(emptyList())

    // searchTextはSavedStateHandleで保持しない
    // メモリ不足などでActivityが再生成された場合はSearchViewの入力状態は失われる
    // searchTextをSavedStateHandleで保持しているとSearchViewの入力状態と検索結果がずれてしまう

    private val _searchText = MutableStateFlow<String>("")

    init {
        searchText = _searchText

        sections = _memos.map { getSections(it) }

        viewModelScope.launch {
            _searchText.flatMapLatest { searchText ->
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
        _searchText.value = query ?: ""
    }

    fun onSearchViewQueryTextChange(newText: String?) {
        _searchText.value = newText ?: ""
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
