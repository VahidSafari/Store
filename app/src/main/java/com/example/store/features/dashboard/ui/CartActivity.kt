package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.R
import kotlinx.android.synthetic.main.activity_cart.*


class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setSupportActionBar(toolbar)

        cl_back_container.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btn_next_step.setOnClickListener {
            startActivity(Intent(this,OrderActivity::class.java))
        }

        rv_cart.layoutManager = LinearLayoutManager(this)
        rv_cart.setHasFixedSize(true)
        val cartAdapter = CartAdapter()
        rv_cart.adapter = cartAdapter
        rv_cart.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        cartAdapter.submitList(
            listOf(
                CartItemView(
                    0,
                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                    " کفش خوب",
                    10000,
                    20000,
                    12,
                    OffPrice(20000,30000),
                    2
                ),
                CartItemView(
                    1,
                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                    " کفش خوب",
                    10000,
                    20000,
                    12,
                    OffPrice(20000,30000),
                    3
                )
            )
        )


    }

}
