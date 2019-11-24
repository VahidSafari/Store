package com.example.store.features.dashboard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.store.R
import com.example.store.databinding.ActivityItemSpecificationBinding
import kotlinx.android.synthetic.main.activity_item_specification.*

class ItemSpecificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityItemSpecificationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_item_specification)
        rv_item_specification.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rv_item_specification.setHasFixedSize(true)
        val itemSpecificationAdapter = ItemSpecificationAdapter(
            listOf(
                PreviewView(
                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png"
                ),
                PreviewView(
                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png"
                )
            )
        )
        rv_item_specification.adapter = itemSpecificationAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_item_specification)
        binding.itemSpecificationView =
            ItemSpecificationView(
                1,
                "کفش خوب",
                10000,
                20000,
                13000,
                17000,
                listOf("قهوه ای","صورتی","سفید"),
                38,
                43
            )

        btn_add_to_cart.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}