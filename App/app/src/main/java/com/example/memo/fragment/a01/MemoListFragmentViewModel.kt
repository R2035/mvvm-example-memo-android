package com.example.memo.fragment.a01

import androidx.lifecycle.SavedStateHandle
import com.example.memo.core.repository.memo.contract.MemoRepository
import com.example.memo.fragment.BaseFragmentViewModel

class MemoListFragmentViewModel(
    savedStateHandle: SavedStateHandle,
    private val memoRepository: MemoRepository,
) : BaseFragmentViewModel() {
    private val _state = savedStateHandle.getStateFlow(MemoListState.initialValue)
}
