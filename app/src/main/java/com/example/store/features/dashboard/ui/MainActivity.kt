package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
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
    private val listFragmentStack = Stack<Int>() // Int -> parent_id of the ListFragment

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
                    }
                }

                R.id.fragment_dashboard -> {
                    if (currentFragment != MainActivityFragmentType.DASHBOARD) {
                        showNextFragmentAndHidePrevious(MainActivityFragmentType.DASHBOARD)
                    }
                }

                R.id.fragment_list -> {
                    if (currentFragment != MainActivityFragmentType.LIST) {
                        showNextFragmentAndHidePrevious(MainActivityFragmentType.LIST)
                    }
                }

                R.id.fragment_search -> {
                    //Checks if the selected fragment is not the same with previous fragment
                    if (currentFragment != MainActivityFragmentType.SEARCH) {
                        showNextFragmentAndHidePrevious(MainActivityFragmentType.SEARCH)
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
                    getFragment(listFragmentTag)?.let {
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
                    getFragment(searchFragmentTag)?.let {
                        supportFragmentManager.beginTransaction()
                            .show(it)
                            .commitNow()
                        bottom_navigation_view.selectedItemId = R.id.fragment_search
                    }
                }

                dashBoardFragmentTag -> {
                    currentFragment = MainActivityFragmentType.DASHBOARD
                    getFragment(dashBoardFragmentTag)?.let {
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

        //Hides previous fragment and pushes it to fragments stack
        getFragment(currentFragment.toString())?.let {
            supportFragmentManager.beginTransaction()
                .hide(it)
                .commitNow()
            if (!isBackPressed)
                fragmentStack.push(it.tag)
            isBackPressed = false
        }

        val nextFragment = getFragment(fragmentType.toString())
        nextFragment?.let {
            currentFragment = fragmentType
            supportFragmentManager.beginTransaction()
                .show(it)
                .commitNow()
        }

        if (fragmentType == MainActivityFragmentType.DASHBOARD) {
            hideAppBarsOnScroll()
        } else showAppBarsOnScroll()

        if (fragmentType == MainActivityFragmentType.SEARCH)
            (nextFragment as SearchFragment).showKeyboard()

    }

    private fun getFragment(fragmentTag: String): Fragment? {
        val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.nav_host_fragment,
                    when (fragmentTag) {
                        MainActivityFragmentType.PROFILE.toString() -> ProfileFragment()
                        MainActivityFragmentType.SEARCH.toString() -> SearchFragment()
                        MainActivityFragmentType.DASHBOARD.toString() -> DashBoardFragment()
                        else -> ListFragment.newInstance(1)
                    },
//                    if (fragmentType == MainActivityFragmentType.LIST )
                    fragmentTag
//                else listFragmentStack.peek().toString()
                )
                .commitNow()
            return supportFragmentManager.findFragmentByTag(fragmentTag)
        } else return fragment
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
