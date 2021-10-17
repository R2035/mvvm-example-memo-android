package com.example.memo.core.repository.memo.contract

import com.example.memo.core.model.Memo
import kotlinx.coroutines.flow.Flow

/**
 * MemoのRepository
 * funの数が増えないようにinputをsealed classにして吸収している
 */
interface MemoRepository {
    suspend fun create(input: MemoRepositoryInputCreate)

    suspend fun delete(input: MemoRepositoryInputDelete)

    fun read(input: MemoRepositoryInputRead): Flow<List<Memo>>

    suspend fun update(input: MemoRepositoryInputUpdate)
}
