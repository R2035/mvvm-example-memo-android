package com.example.memo.core.repository.memo.implementation

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RoomMemoDao {
    @Query("SELECT * FROM memos")
    fun selectAll(): Flow<List<RoomMemo>>

    @Query("SELECT * FROM memos WHERE body LIKE'%' || :body || '%'")
    fun select(body: String): Flow<List<RoomMemo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(memo: RoomMemo)

    @Update
    suspend fun update(memo: RoomMemo)

    @Query("DELETE FROM memos WHERE id = :id")
    suspend fun delete(id: String)
}
