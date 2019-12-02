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
            this.onBackPressed()
        }

        btn_next_step.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }

        val cartAdapter = CartAdapter()
        rv_cart.apply {
            layoutManager = LinearLayoutManager(this@CartActivity)
            setHasFixedSize(true)
            adapter = cartAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@CartActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        val adapterList =
            mutableListOf(
                CartItemView(
                    1,
                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                    " کفش عالی",
                    40000,
                    20000,
                    12,
                    OffPrice(50000, 40000),
                    2
                ),
                CartItemView(
                    2,
                    "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                    " کفش خوب",
                    10000,
                    30000,
                    12,
                    OffPrice(60000, 45000),
                    3
                ),
                CartItemView(
                    3,
                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                    " کفش ورزشی",
                    10000,
                    30000,
                    12,
                    OffPrice(60000, 45000),
                    3
                ),
                CartItemView(
                    4,
                    "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                    " کفش آدیداس",
                    10000,
                    30000,
                    12,
                    OffPrice(60000, 45000),
                    6
                ),
                CartItemView(
                    5,
                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                    " کفش راحتی",
                    20000,
                    60000,
                    12,
                    OffPrice(60000, 45000),
                    2
                )

            )

        cartAdapter.submitList(adapterList)
        cartAdapter.remove = {
            adapterList.removeAt(it)
            cartAdapter.submitList(adapterList)
            cartAdapter.notifyItemRemoved(it)
            cartAdapter.notifyItemRangeChanged(it, adapterList.size)
        }
    }
}