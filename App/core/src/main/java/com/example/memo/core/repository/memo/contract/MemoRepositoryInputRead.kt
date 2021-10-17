package com.example.memo.core.repository.memo.contract

sealed class MemoRepositoryInputRead {
    object All : MemoRepositoryInputRead()

    data class Contains(val body: String) : MemoRepositoryInputRead()
}
