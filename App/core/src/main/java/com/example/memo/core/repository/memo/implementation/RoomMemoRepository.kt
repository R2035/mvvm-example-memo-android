package com.example.memo.core.repository.memo.implementation

import com.example.memo.core.model.Memo
import com.example.memo.core.model.MemoId
import com.example.memo.core.repository.memo.contract.MemoRepository
import com.example.memo.core.repository.memo.contract.MemoRepositoryInputCreate
import com.example.memo.core.repository.memo.contract.MemoRepositoryInputDelete
import com.example.memo.core.repository.memo.contract.MemoRepositoryInputRead
import com.example.memo.core.repository.memo.contract.MemoRepositoryInputUpdate
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomMemoRepository(
    private val roomMemoDao: RoomMemoDao,
) : MemoRepository {
    override suspend fun create(input: MemoRepositoryInputCreate) {
        when (input) {
            is MemoRepositoryInputCreate.Memo -> {
                val roomMemo = RoomMemo(id = UUID.randomUUID().toString(), input.body)
                roomMemoDao.insert(roomMemo)
            }
        }
    }

    override suspend fun delete(input: MemoRepositoryInputDelete) {
        when (input) {
            is MemoRepositoryInputDelete.Memo -> {
                roomMemoDao.delete(input.id)
            }
        }
    }

    override fun read(input: MemoRepositoryInputRead): Flow<List<Memo>> {
        return when (input) {
            is MemoRepositoryInputRead.All -> {
                roomMemoDao.selectAll()
            }
            is MemoRepositoryInputRead.Contains -> {
                roomMemoDao.select(input.body)
            }
        }.map { roomMemos ->
            roomMemos.map { convert(it) }
        }
    }

    override suspend fun update(input: MemoRepositoryInputUpdate) {
        when (input) {
            is MemoRepositoryInputUpdate.Memo -> {
                val roomMemo = RoomMemo(input.memo.id.value, input.memo.body)
                roomMemoDao.update(roomMemo)
            }
        }
    }

    companion object {
        private fun convert(roomMemo: RoomMemo): Memo {
            return Memo(MemoId(roomMemo.id), roomMemo.body)
        }
    }
}
