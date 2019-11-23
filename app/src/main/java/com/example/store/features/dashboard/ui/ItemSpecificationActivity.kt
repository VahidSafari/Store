package com.example.store.features.dashboard.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.R
import kotlinx.android.synthetic.main.activity_item_specification.*

class ItemSpecificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_specification)

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
    }
}