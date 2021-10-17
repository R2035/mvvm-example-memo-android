package com.example.memo.fragment.a02

import androidx.lifecycle.SavedStateHandle
import com.example.memo.core.repository.memo.contract.MemoRepository
import com.example.memo.fragment.BaseFragmentViewModel

class EditingMemoViewModel(
    savedStateHandle: SavedStateHandle,
    private val memoRepository: MemoRepository,
) : BaseFragmentViewModel() {
    private val _state = savedStateHandle.getStateFlow(EditingMemoState.initialValue)
}
