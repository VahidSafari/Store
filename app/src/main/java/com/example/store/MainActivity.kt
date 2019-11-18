package com.example.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.store.features.User.ProfileFragment
import com.example.store.features.dashboard.ui.DashBoardFragment
import com.example.store.features.dashboard.ui.ListFragment
import com.example.store.features.dashboard.ui.SearchFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : DaggerAppCompatActivity() {

    private val profileFragment = ProfileFragment()
    private val searchFragment = SearchFragment()
    private val listFragment = ListFragment()
    private val dashBoardFragment = DashBoardFragment()
    private var currentFragment: Fragment? = dashBoardFragment
    private val profileFragmentTag = "fragment_profile"
    private val searchFragmentTag = "fragment_search"
    private val listFragmentTag = "fragment_list"
    private val dashBoardFragmentTag = "fragment_dashboard"
    private val fragmentStack = Stack<String>()
    private var isBackPressed = false
    val ft = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation_view.menu.getItem(2).isEnabled = false

        ft.add(R.id.nav_host_fragment, profileFragment, profileFragmentTag)
        ft.add(R.id.nav_host_fragment, searchFragment, searchFragmentTag)
        ft.add(R.id.nav_host_fragment, listFragment, listFragmentTag)
        ft.add(R.id.nav_host_fragment, dashBoardFragment, dashBoardFragmentTag)
        ft.commit()

        bottom_navigation_view.selectedItemId = R.id.fragment_dashboard

        bottom_navigation_view.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.fragment_profile -> {

                    if (currentFragment?.tag != profileFragmentTag) {
                        currentFragment?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .commit()
                            if (!isBackPressed)
                                fragmentStack.push(fragment.tag)
                            isBackPressed = false
//                            backStackCount++
                        }

                        currentFragment = profileFragment
                        supportFragmentManager.beginTransaction()
                            .show(profileFragment)
                            .commit()
                    }

                }

                R.id.fragment_dashboard -> {

                    if (currentFragment?.tag != dashBoardFragmentTag) {
                        currentFragment?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .commit()
                            if (!isBackPressed)
                                fragmentStack.push(fragment.tag)
                            isBackPressed = false
//                            backStackCount++
                        }
                        currentFragment = dashBoardFragment

                        supportFragmentManager.beginTransaction()
                            .show(dashBoardFragment)
                            .commit()
                    }

                }

                R.id.fragment_list -> {

                    if (currentFragment?.tag != listFragmentTag) {
                        currentFragment?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .commit()
                            if (!isBackPressed)
                                fragmentStack.push(fragment.tag)
                            isBackPressed = false
//                            backStackCount++
                        }
                        currentFragment = listFragment

                        supportFragmentManager.beginTransaction()
                            .show(listFragment)
                            .commit()
                    }

                }

                R.id.fragment_search -> {

                    if (currentFragment?.tag != searchFragmentTag) {
                        currentFragment?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .commit()
                            if (!isBackPressed)
                                fragmentStack.push(fragment.tag)
                            isBackPressed = false
//                            backStackCount++
                        }
                        currentFragment = searchFragment

                        supportFragmentManager.beginTransaction()
                            .show(searchFragment)
                            .commit()
                    }
                }
            }

            return@setOnNavigationItemSelectedListener true
        }


    }

    override fun onBackPressed() {
        if (fragmentStack.size > 0) {
            isBackPressed = true
            when (fragmentStack.pop()) {
                listFragmentTag -> {
                    currentFragment = listFragment
                    supportFragmentManager.beginTransaction()
                        .show(listFragment)
                        .commit()
                    bottom_navigation_view.selectedItemId = R.id.fragment_list

                }
                profileFragmentTag -> {
                    currentFragment = profileFragment
                    currentFragment = listFragment
                    supportFragmentManager.beginTransaction()
                        .show(profileFragment)
                        .commit()
                    bottom_navigation_view.selectedItemId = R.id.fragment_profile

                }
                searchFragmentTag -> {
                    currentFragment = searchFragment
                    currentFragment = listFragment
                    supportFragmentManager.beginTransaction()
                        .show(searchFragment)
                        .commit()
                    bottom_navigation_view.selectedItemId = R.id.fragment_search

                }
                dashBoardFragmentTag -> {
                    currentFragment = dashBoardFragment
                    currentFragment = listFragment
                    supportFragmentManager.beginTransaction()
                        .show(dashBoardFragment)
                        .commit()
                    bottom_navigation_view.selectedItemId = R.id.fragment_dashboard

                }
            }

        } else {
            super.onBackPressed()
        }
    }
}
