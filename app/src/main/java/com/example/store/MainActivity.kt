package com.example.store

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.store.features.dashboard.ui.DashBoardFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_host_fragment, DashBoardFragment()
        ).commit()

        bottom_navigation_view.menu.getItem(2).isEnabled = false
        bottom_navigation_view.selectedItemId = R.id.fragment_home

        bottom_navigation_view.setOnNavigationItemSelectedListener {

            when(it.itemId){
                R.id.fragment_profile -> {
                    this.findNavController(
                        R.id.nav_host_fragment
                    ).navigate(
                        R.id.action_to_profileFragment
                    )
                }

                R.id.fragment_home -> {
                    this.findNavController(
                        R.id.nav_host_fragment
                    ).navigate(
                        R.id.action_to_homeFragment
                    )
                }

                R.id.fragment_list -> {
                    this.findNavController(
                        R.id.nav_host_fragment
                    ).navigate(
                        R.id.action_to_listFragment
                    )
                }

                R.id.fragment_search -> {
                    this.findNavController(
                        R.id.nav_host_fragment
                    ).navigate(
                        R.id.action_to_searchFragment
                    )
                }
                else -> { }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

}
