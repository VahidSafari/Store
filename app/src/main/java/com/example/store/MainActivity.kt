package com.example.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.store.features.DashBoardFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(
            R.id.nav_host_fragment,
            DashBoardFragment()
        )
    }
}
