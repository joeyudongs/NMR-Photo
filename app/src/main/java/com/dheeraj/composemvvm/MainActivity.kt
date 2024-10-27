package com.dheeraj.composemvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dheeraj.composemvvm.databinding.ActivityMainBinding
import com.dheeraj.composemvvm.fragment.BaseFragment
import com.dheeraj.composemvvm.fragment.ComposeFragment
import com.dheeraj.composemvvm.fragment.KotlinFragment
import com.dheeraj.composemvvm.viewmodel.ItemViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),BaseFragment.SortCallback {
    // Using the viewModels() Kotlin property delegate from the activity-ktx
    // artifact to retrieve the ViewModel in the activity scope.
    private val viewModel: ItemViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)

        val bar = binding.toolbar

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
        // find current fragment
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame_content)
        if (currentFragment is BaseFragment) {
            // call the sort method
            currentFragment.sortData(isAscend)
        }
    }

}
