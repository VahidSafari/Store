package com.example.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.store.features.User.ProfileFragment
import com.example.store.features.dashboard.ui.DashBoardFragment
import com.example.store.features.dashboard.ui.ListFragment
import com.example.store.features.dashboard.ui.SearchFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    private val profileFragment = ProfileFragment()
    private val searchFragment = SearchFragment()
    private val listFragment = ListFragment()
    private val dashBoardFragment = DashBoardFragment()
    private var currentFragment: Fragment? = dashBoardFragment
    private var backStackCount = supportFragmentManager.backStackEntryCount
    private val profileFragmentTag = "fragment_profile"
    private val searchFragmentTag = "fragment_search"
    private val listFragmentTag = "fragment_list"
    private val dashBoardFragmentTag = "fragment_home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        supportFragmentManager.beginTransaction().replace(
//            R.id.nav_host_fragment, DashBoardFragment()
//        ).commit()

        bottom_navigation_view.menu.getItem(2).isEnabled = false


        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.nav_host_fragment, profileFragment, profileFragmentTag)
        ft.add(R.id.nav_host_fragment, searchFragment, searchFragmentTag)
        ft.add(R.id.nav_host_fragment, listFragment, listFragmentTag)
        ft.add(R.id.nav_host_fragment, dashBoardFragment, dashBoardFragmentTag)
        ft.commit()

        bottom_navigation_view.selectedItemId = R.id.fragment_home


        bottom_navigation_view.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.fragment_profile -> {

                    if (currentFragment?.tag != profileFragmentTag) {
                        currentFragment?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .addToBackStack((currentFragment as Fragment).tag)
                                .commit()
                            backStackCount++
                        }

                        currentFragment = profileFragment
                        supportFragmentManager.beginTransaction()
                            .show(profileFragment)
                            .commit()
                    }

                }

                R.id.fragment_home -> {

                    if (currentFragment?.tag != dashBoardFragmentTag) {
                        currentFragment?.let { fragment ->
                            supportFragmentManager.beginTransaction()
                                .hide(fragment)
                                .addToBackStack((currentFragment as Fragment).tag)
                                .commit()
                            backStackCount++
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
                                .addToBackStack((currentFragment as Fragment).tag)
                                .commit()
                            backStackCount++
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
                                .addToBackStack((currentFragment as Fragment).tag)
                                .commit()
                            backStackCount++
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



        supportFragmentManager.addOnBackStackChangedListener {
            if (supportFragmentManager.backStackEntryCount in 1 until backStackCount) {
                val currentNavigationItemName =
                    supportFragmentManager.getBackStackEntryAt(
                        supportFragmentManager.backStackEntryCount - 1
                    ).name
                when (currentNavigationItemName) {
                    "ListFragment" -> {
                        bottom_navigation_view.selectedItemId = R.id.fragment_list
                    }
                    "ProfileFragment" -> {
                        bottom_navigation_view.selectedItemId = R.id.fragment_profile
                    }
                    "SearchFragment" -> {
                        bottom_navigation_view.selectedItemId = R.id.fragment_search
                    }
                    "DashBoardFragment" -> {
                        bottom_navigation_view.selectedItemId = R.id.fragment_home
                    }
                }
                backStackCount--
            }
        }

    }


}
