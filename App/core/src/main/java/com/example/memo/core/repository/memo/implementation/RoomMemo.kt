package com.example.memo.core.repository.memo.implementation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memos")
internal data class RoomMemo(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo(name = "body")
    val body: String,
)
