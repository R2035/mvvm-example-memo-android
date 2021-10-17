package com.example.memo.fragment.a02

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.memo.core.repository.memo.contract.MemoRepository
import com.example.memo.core.repository.memo.contract.MemoRepositoryInputCreate
import com.example.memo.fragment.BaseFragmentViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class EditingMemoViewModel(
    savedStateHandle: SavedStateHandle,
    private val memoRepository: MemoRepository,
) : BaseFragmentViewModel() {
    val body: Flow<String>

    private val _state = savedStateHandle.getStateFlow(EditingMemoState.initialValue)

    init {
        body = _state.map { it.body }
    }

    fun bodyEditTextAfterTextChanged(text: String) {
        _state.value = _state.value.copy(
            body = text
        )
    }

    fun onSaveOptionsItemSelected() {
        viewModelScope.launch {
            val body = _state.value.body
            memoRepository.create(MemoRepositoryInputCreate.Memo(body))
            pop()
        }
    }

    fun onDeleteOptionsItemSelected() {
        viewModelScope.launch {
            // TODO: 削除処理を実装
            pop()
        }
    }
}
