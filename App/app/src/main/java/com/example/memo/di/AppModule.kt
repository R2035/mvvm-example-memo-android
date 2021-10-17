package com.example.memo.di

import com.example.memo.fragment.a01.MemoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {
    fun create(): Module {
        return module {
            viewModel { MemoListViewModel(get(), get()) }
        }
    }
}
