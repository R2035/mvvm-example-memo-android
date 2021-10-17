package com.example.memo.application

import android.app.Application
import com.example.memo.core.di.CoreModule
import com.example.memo.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MemoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@MemoApplication)
            modules(
                AppModule.create(),
                CoreModule.create(),
            )
        }
    }
}
