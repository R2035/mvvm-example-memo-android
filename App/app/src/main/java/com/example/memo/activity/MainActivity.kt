package com.example.memo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.memo.R

/**
 * アプリ起動中は常に表示されるActivity
 * Activityは他に使用しない
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
