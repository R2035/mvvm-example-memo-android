package com.example.memo.fragment.a02

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.memo.core.model.Memo
import com.example.memo.core.repository.memo.contract.MemoRepository
import com.example.memo.core.repository.memo.contract.MemoRepositoryInputCreate
import com.example.memo.core.repository.memo.contract.MemoRepositoryInputDelete
import com.example.memo.core.repository.memo.contract.MemoRepositoryInputUpdate
import com.example.memo.fragment.BaseFragmentViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class EditingMemoViewModel(
    args: EditingMemoFragmentArgs,
    private val memoRepository: MemoRepository,
    savedStateHandle: SavedStateHandle,
) : BaseFragmentViewModel() {
    val body: Flow<String>

    val isDeleteOptionsItemVisible: Flow<Boolean>

    private val _state = savedStateHandle.getStateFlow(EditingMemoState(args.value.memo?.body ?: ""))

    private val _isDeleteOptionsItemVisible: MutableStateFlow<Boolean>

    private val memoId = args.value.memo?.id

    init {
        body = _state.map { it.body }

        _isDeleteOptionsItemVisible = MutableStateFlow(args.value.memo != null)

        isDeleteOptionsItemVisible = _isDeleteOptionsItemVisible
    }

    fun bodyEditTextAfterTextChanged(text: String) {
        _state.value = _state.value.copy(
            body = text
        )
    }

    fun onSaveOptionsItemSelected() {
        viewModelScope.launch {
            val memoId = memoId
            val body = _state.value.body
            if (memoId != null) {
                val memo = Memo(memoId, body)
                memoRepository.update(MemoRepositoryInputUpdate.Memo(memo))
            } else {
                memoRepository.create(MemoRepositoryInputCreate.Memo(body))
            }
            pop()
        }
    }

    fun onDeleteOptionsItemSelected() {
        val memoId = memoId ?: throw IllegalStateException("Delete options item cannot be selected when memoId is null.")
        viewModelScope.launch {
            memoRepository.delete(MemoRepositoryInputDelete.Memo(memoId))
            pop()
        }
    }
}
