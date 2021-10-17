package com.example.memo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.memo.R
import com.example.memo.databinding.ActivityMainBinding

/**
 * アプリ起動中は常に表示されるActivity
 * Activityは他に使用しない
 */
class MainActivity : AppCompatActivity() {
    private val navController: NavController
        get() = (supportFragmentManager.findFragmentById(R.id.main_fragment_container_view) as NavHostFragment).navController

    private val appBarConfiguration = AppBarConfiguration
        .Builder(
            R.id.a01,
        )
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}
