package com.example.memo.core.di

import androidx.room.Room
import com.example.memo.core.repository.database.MemoDatabase
import com.example.memo.core.repository.memo.contract.MemoRepository
import com.example.memo.core.repository.memo.implementation.RoomMemoRepository
import org.koin.core.module.Module
import org.koin.dsl.module

object CoreModule {
    fun create(): Module {
        return module {
            single { Room.databaseBuilder(get(), MemoDatabase::class.java, "memo.db").build() }

            single { get<MemoDatabase>().memoDao() }

            single<MemoRepository> { RoomMemoRepository(get()) }
        }
    }
}
