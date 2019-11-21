package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.R
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        cl_order_back_container.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }

        btn_order.setOnClickListener {
            startActivity(Intent(this, OrderSuccessActivity::class.java))
        }

        rv_order.layoutManager = LinearLayoutManager(this)
        rv_order.setHasFixedSize(true)
        val orderAdapter = OrderAdapter()
        rv_order.adapter = orderAdapter
        rv_order.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        orderAdapter.submitList(
            listOf(
                ProductView(
                    1,
                    "کفش مناسب",
                    20000,
                    10000,
                    3
                ),
                ProductView(
                    2,
                    "کفش نامناسب",
                    15000,
                    10000,
                    2
                )
            )
        )

    }
}
