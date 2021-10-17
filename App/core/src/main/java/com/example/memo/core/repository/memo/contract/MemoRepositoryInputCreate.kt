package com.example.memo.core.repository.memo.contract

sealed class MemoRepositoryInputCreate {
    data class Memo(val body: String) : MemoRepositoryInputCreate()
}
