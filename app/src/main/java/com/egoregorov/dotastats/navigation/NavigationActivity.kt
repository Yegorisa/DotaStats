package com.egoregorov.dotastats.navigation

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.egoregorov.dotastats.R
import com.egoregorov.dotastats.navigation.home.HomeFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel



class NavigationActivity : AppCompatActivity() {

    val navigationViewModel: NavigationViewModel by viewModel()

    val homeFragment : HomeFragment by inject()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(navigationActivityFrameLayout.id, homeFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(navigationActivityFrameLayout.id, homeFragment).commit()
    }
}
