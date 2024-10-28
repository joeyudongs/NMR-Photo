package com.dheeraj.composemvvm

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.transition.Explode
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.dheeraj.composemvvm.databinding.ActivityMainBinding
import com.dheeraj.composemvvm.fragment.BaseFragment
import com.dheeraj.composemvvm.fragment.ComposeFragment
import com.dheeraj.composemvvm.fragment.KotlinFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(),BaseFragment.SortCallback {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.BLACK

            with(window) {
                requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

                // Set an exit transition
                exitTransition = Explode()
            }
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)

        val bar = binding.toolbar

        bar.setBackgroundColor(resources.getColor(android.R.color.black))
        bar.overflowIcon = resources.getDrawable(R.mipmap.ic_menu_sort)
        bar.inflateMenu(R.menu.bar_sort_menu)

        bar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.bar_sort_ascend -> {
                    onSort(true)
                    true
                }
                R.id.bar_sort_descend -> {
                    onSort(false)

                    true
                }
                else -> false
            } }

        // Set the initial fragment to KotlinFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_content, KotlinFragment())
                .commit()
        }

        bottomNavigationView.setBackgroundColor(resources.getColor(android.R.color.black))
        // Listener for BottomNavigationView item selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_kotlin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_content, KotlinFragment())
                        .commit()
                    true
                }
                R.id.nav_compose -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_content, ComposeFragment())
                        .commitAllowingStateLoss()
                    true
                }
                else -> false
            }
        }

    }

    override fun onSort(isAscend: Boolean) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_content)
        if (currentFragment is BaseFragment) {
            currentFragment.sortData(isAscend)
        }
    }

}
