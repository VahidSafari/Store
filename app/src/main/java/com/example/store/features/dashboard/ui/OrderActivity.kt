package com.example.store.features.dashboard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.store.R
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        cl_order_back_container.setOnClickListener {
            startActivity(Intent(this,CartActivity::class.java))
        }

        btn_order.setOnClickListener {
            startActivity(Intent(this, OrderSuccessActivity::class.java))
        }

    }
}
