package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.store.R
import com.example.store.features.user.ProfileFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : DaggerAppCompatActivity() {

    private var currentFragment: String = "fragment_dashboard"
    private val profileFragmentTag = "fragment_profile"
    private val searchFragmentTag = "fragment_search"
    private val listFragmentTag = "fragment_list"
    private val dashBoardFragmentTag = "fragment_dashboard"
    private val fragmentStack = Stack<String>()
    private var isBackPressed = false
    private var exitApp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation_view.menu.getItem(2).isEnabled = false

        bottom_navigation_view.selectedItemId = R.id.fragment_dashboard

        supportFragmentManager.beginTransaction()
            .add(
                R.id.nav_host_fragment,
                DashBoardFragment(),
                dashBoardFragmentTag
            )
            .commitNow()

        fab.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        bottom_navigation_view.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.fragment_profile -> {

                    if (currentFragment != profileFragmentTag) {
                        exitApp = false
                        val profileFragment =
                            supportFragmentManager.findFragmentByTag(profileFragmentTag)
                        if (profileFragment == null) {
                            supportFragmentManager.beginTransaction()
                                .add(R.id.nav_host_fragment, ProfileFragment(), profileFragmentTag)
                                .commitNow()
                        }

                        supportFragmentManager.findFragmentByTag(currentFragment)?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .commitNow()
                            if (!isBackPressed)
                                fragmentStack.push(fragment.tag)
                            isBackPressed = false
                        }

                        supportFragmentManager.findFragmentByTag(profileFragmentTag)
                            ?.let { fragment ->
                                currentFragment = profileFragmentTag
                                supportFragmentManager.beginTransaction()
                                    .show(fragment)
                                    .commitNow()
                            }
                        supportFragmentManager.beginTransaction().commitNow()
                    }
                }

                R.id.fragment_dashboard -> {

                    if (currentFragment != dashBoardFragmentTag) {
                        exitApp = false
                        val dashboardFragment =
                            supportFragmentManager.findFragmentByTag(dashBoardFragmentTag)
                        if (dashboardFragment == null) {
                            supportFragmentManager.beginTransaction()
                                .add(
                                    R.id.nav_host_fragment,
                                    DashBoardFragment(),
                                    dashBoardFragmentTag
                                )
                                .commitNow()
                        }

                        supportFragmentManager.findFragmentByTag(currentFragment)?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .commitNow()
                            if (!isBackPressed)
                                fragmentStack.push(fragment.tag)
                            isBackPressed = false
                        }

                        supportFragmentManager.findFragmentByTag(dashBoardFragmentTag)
                            ?.let { fragment ->
                                currentFragment = dashBoardFragmentTag
                                supportFragmentManager.beginTransaction()
                                    .show(fragment)
                                    .commitNow()
                            }
                        supportFragmentManager.beginTransaction().commitNow()
                    }

                }

                R.id.fragment_list -> {

                    if (currentFragment != listFragmentTag) {
                        exitApp = false
                        val listFragment =
                            supportFragmentManager.findFragmentByTag(listFragmentTag)
                        if (listFragment == null) {
                            supportFragmentManager.beginTransaction()
                                .add(R.id.nav_host_fragment, ListFragment(), listFragmentTag)
                                .commitNow()
                        }

                        supportFragmentManager.findFragmentByTag(currentFragment)?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .commitNow()
                            if (!isBackPressed)
                                fragmentStack.push(fragment.tag)
                            isBackPressed = false
                        }

                        supportFragmentManager.findFragmentByTag(listFragmentTag)?.let { fragment ->

                            currentFragment = listFragmentTag
                            supportFragmentManager.beginTransaction()
                                .show(fragment)
                                .commitNow()
                        }
                        supportFragmentManager.beginTransaction().commitNow()
                    }

                }

                R.id.fragment_search -> {

                    if (currentFragment != searchFragmentTag) {
                        exitApp = false
                        val searchFragment =
                            supportFragmentManager.findFragmentByTag(searchFragmentTag)
                        if (searchFragment == null) {
                            supportFragmentManager.beginTransaction()
                                .add(R.id.nav_host_fragment, SearchFragment(), searchFragmentTag)
                                .commitNow()
                        }

                        supportFragmentManager.findFragmentByTag(currentFragment)?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .commitNow()
                            if (!isBackPressed)
                                fragmentStack.push(fragment.tag)
                            isBackPressed = false
                        }

                        supportFragmentManager.findFragmentByTag(searchFragmentTag)
                            ?.let { fragment ->
                                currentFragment = searchFragmentTag
                                supportFragmentManager.beginTransaction()
                                    .show(fragment)
                                    .commitNow()
                            }
                    }
                }

            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        if (fragmentStack.size > 0) {
            isBackPressed = true
            exitApp = false

            supportFragmentManager.findFragmentByTag(currentFragment)?.let {
                supportFragmentManager.beginTransaction()
                    .hide(it)
                    .commitNow()
            }

            when (fragmentStack.pop()) {
                listFragmentTag -> {
                    currentFragment = listFragmentTag
                    supportFragmentManager.findFragmentByTag(listFragmentTag)?.let {
                        supportFragmentManager.beginTransaction()
                            .show(it)
                            .commitNow()
                        bottom_navigation_view.selectedItemId = R.id.fragment_list
                    }
                }
                profileFragmentTag -> {
                    currentFragment = profileFragmentTag
                    supportFragmentManager.findFragmentByTag(profileFragmentTag)?.let {
                        supportFragmentManager.beginTransaction()
                            .show(it)
                            .commitNow()
                        bottom_navigation_view.selectedItemId =
                            R.id.fragment_profile
                    }
                }
                searchFragmentTag -> {
                    currentFragment = searchFragmentTag
                    supportFragmentManager.findFragmentByTag(searchFragmentTag)?.let {
                        supportFragmentManager.beginTransaction()
                            .show(it)
                            .commitNow()
                        bottom_navigation_view.selectedItemId =
                            R.id.fragment_search
                    }
                }
                dashBoardFragmentTag -> {
                    currentFragment = dashBoardFragmentTag
                    supportFragmentManager.findFragmentByTag(dashBoardFragmentTag)?.let {
                        supportFragmentManager.beginTransaction()
                            .show(it)
                            .commitNow()
                        bottom_navigation_view.selectedItemId =
                            R.id.fragment_dashboard

                    }
                }
            }

        } else {
            if (exitApp) {
                super.onBackPressed()
            } else {
                Toast.makeText(
                    applicationContext
                    , " برای خروج دوباره بازگشت را فشار دهید",
                    Toast.LENGTH_SHORT
                )
                    .show()
                exitApp = true
                bottom_navigation_view.selectedItemId = R.id.fragment_dashboard
            }
        }
    }
}
