package com.example.memo.core.repository.memo.contract

import com.example.memo.core.model.MemoId

sealed class MemoRepositoryInputDelete {
    data class Memo(val id: MemoId) : MemoRepositoryInputDelete()
}
