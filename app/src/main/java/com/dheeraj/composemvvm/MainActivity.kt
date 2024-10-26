package com.dheeraj.composemvvm

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dheeraj.composemvvm.fragment.KotlinFragment
import com.dheeraj.composemvvm.viewmodel.CreditCardViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val viewModel: CreditCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)

        // Set the initial fragment to KotlinFragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, KotlinFragment())
                .commit()
        }

        // Listener for BottomNavigationView item selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_kotlin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, KotlinFragment())
                        .commit()
                    true
                }
                R.id.nav_compose -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, KotlinFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

    }

}