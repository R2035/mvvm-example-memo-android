package com.example.memo.core.repository.memo.contract

sealed class MemoRepositoryInputUpdate {
    data class Memo(val memo: com.example.memo.core.model.Memo) : MemoRepositoryInputUpdate()
}
