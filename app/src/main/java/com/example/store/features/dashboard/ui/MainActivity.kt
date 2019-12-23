package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.store.R
import com.example.store.databinding.ActivityMainBinding
import com.example.store.features.user.ProfileFragment
import com.google.android.material.appbar.AppBarLayout
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : DaggerAppCompatActivity() {

    private var currentFragment: MainActivityFragmentType = MainActivityFragmentType.DASHBOARD
    private val profileFragmentTag = MainActivityFragmentType.PROFILE.toString()
    private val searchFragmentTag = MainActivityFragmentType.SEARCH.toString()
    private val listFragmentTag = MainActivityFragmentType.LIST.toString()
    private val dashBoardFragmentTag = MainActivityFragmentType.DASHBOARD.toString()
    private val fragmentStack = Stack<String>()
    private var isBackPressed = false
    private var exitApp = false
    private val listFragmentStack = Stack<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.tb_dashboard)
        setSupportActionBar(toolbar)

        bottom_navigation_view.menu.getItem(2).isEnabled = false

        bottom_navigation_view.selectedItemId = R.id.fragment_dashboard

        tb_dashboard.setNavigationOnClickListener {
            bottom_navigation_view.selectedItemId = R.id.fragment_search
        }

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

                    if (currentFragment != MainActivityFragmentType.PROFILE) {

                        showNextFragmentAndHidePrevious(MainActivityFragmentType.PROFILE)

                        supportFragmentManager.findFragmentByTag(profileFragmentTag)
                            ?.let { fragment ->

                                showAppBarsOnScroll()

                                currentFragment = MainActivityFragmentType.PROFILE
                                supportFragmentManager.beginTransaction()
                                    .show(fragment)
                                    .commitNow()
                            }
                    }
                }

                R.id.fragment_dashboard -> {

                    if (currentFragment != MainActivityFragmentType.DASHBOARD) {

                        showNextFragmentAndHidePrevious(MainActivityFragmentType.DASHBOARD)

                        supportFragmentManager.findFragmentByTag(dashBoardFragmentTag)
                            ?.let { fragment ->

                                hideAppBarsOnScroll()

                                currentFragment = MainActivityFragmentType.DASHBOARD
                                supportFragmentManager.beginTransaction()
                                    .show(fragment)
                                    .commitNow()
                            }

                    }
                }


                R.id.fragment_list -> {

                    if (currentFragment != MainActivityFragmentType.LIST) {

                        showNextFragmentAndHidePrevious(MainActivityFragmentType.LIST)

                        supportFragmentManager.findFragmentByTag(listFragmentTag)?.let { fragment ->

                            showAppBarsOnScroll()

                            currentFragment = MainActivityFragmentType.LIST
                            supportFragmentManager.beginTransaction()
                                .show(fragment)
                                .commitNow()
                        }

                    }
                }


                R.id.fragment_search -> {

                    //Checks if the selected fragment is not the same with previous fragment
                    if (currentFragment != MainActivityFragmentType.SEARCH) {

                        showNextFragmentAndHidePrevious(MainActivityFragmentType.SEARCH)

                        //Prevents app bar and bottom app bar from hiding on scroll behaviour
                        //shows the fragment
                        supportFragmentManager.findFragmentByTag(searchFragmentTag)
                            ?.let { fragment ->

                                showAppBarsOnScroll()

                                currentFragment = MainActivityFragmentType.SEARCH
                                supportFragmentManager.beginTransaction()
                                    .show(fragment)
                                    .commitNow()
                                (fragment as SearchFragment).showKeyboard()
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

            //Hides current fragment
            supportFragmentManager.findFragmentByTag(currentFragment.toString())?.let {
                supportFragmentManager.beginTransaction()
                    .hide(it)
                    .commitNow()
            }

            //Pops a fragment from stack and shows it
            when (fragmentStack.pop()) {
                listFragmentTag -> {
                    currentFragment = MainActivityFragmentType.LIST
                    supportFragmentManager.findFragmentByTag(listFragmentTag)?.let {
                        supportFragmentManager.beginTransaction()
                            .show(it)
                            .commitNow()
                        bottom_navigation_view.selectedItemId = R.id.fragment_list
                    }
                }

                profileFragmentTag -> {
                    currentFragment = MainActivityFragmentType.PROFILE
                    supportFragmentManager.findFragmentByTag(profileFragmentTag)?.let {
                        supportFragmentManager.beginTransaction()
                            .show(it)
                            .commitNow()
                        bottom_navigation_view.selectedItemId = R.id.fragment_profile
                    }
                }

                searchFragmentTag -> {
                    currentFragment = MainActivityFragmentType.SEARCH
                    supportFragmentManager.findFragmentByTag(searchFragmentTag)?.let {
                        supportFragmentManager.beginTransaction()
                            .show(it)
                            .commitNow()
                        bottom_navigation_view.selectedItemId = R.id.fragment_search
                    }
                }

                dashBoardFragmentTag -> {
                    currentFragment = MainActivityFragmentType.DASHBOARD
                    supportFragmentManager.findFragmentByTag(dashBoardFragmentTag)?.let {
                        supportFragmentManager.beginTransaction()
                            .show(it)
                            .commitNow()
                        bottom_navigation_view.selectedItemId = R.id.fragment_dashboard
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

    private fun showNextFragmentAndHidePrevious(fragmentType: MainActivityFragmentType) {

        //prevents app from exiting
        exitApp = false

        //Creates the fragment if it has not been created yet
        val nextFragment =
            supportFragmentManager.findFragmentByTag(fragmentType.toString())
        if (nextFragment == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.nav_host_fragment,
                    when (fragmentType) {
                        MainActivityFragmentType.PROFILE -> ProfileFragment()
                        MainActivityFragmentType.SEARCH -> SearchFragment()
                        MainActivityFragmentType.LIST -> {
                            ListFragment.newInstance(1)
                        }
                        MainActivityFragmentType.DASHBOARD -> DashBoardFragment()
                    },
                    fragmentType.toString()
                )
                .commitNow()
        }

        //Hides previous fragment and pushes it to fragments stack
        supportFragmentManager.findFragmentByTag(currentFragment.toString())?.let { fragment ->
            supportFragmentManager.beginTransaction()
                .hide(fragment)
                .commitNow()
            if (!isBackPressed)
                fragmentStack.push(fragment.tag)
            isBackPressed = false
        }
    }

    private fun hideAppBarsOnScroll() {
        val params =
            tb_dashboard?.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or
                AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP)
        tb_dashboard?.layoutParams = params
        bap_main.hideOnScroll = true
    }

    private fun showAppBarsOnScroll() {
        val params =
            tb_dashboard?.layoutParams as AppBarLayout.LayoutParams
        params.scrollFlags = 0
        tb_dashboard?.layoutParams = params
        bap_main?.hideOnScroll = false
    }

}
