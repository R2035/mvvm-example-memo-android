package com.example.memo.core.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.memo.core.repository.memo.implementation.RoomMemo
import com.example.memo.core.repository.memo.implementation.RoomMemoDao

@Database(
    entities = [RoomMemo::class],
    version = 1
)
internal abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao(): RoomMemoDao
}
