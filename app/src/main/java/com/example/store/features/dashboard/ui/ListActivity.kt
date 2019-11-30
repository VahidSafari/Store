package com.example.store.features.dashboard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.R
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        rv_list.apply {
            layoutManager = LinearLayoutManager(this@ListActivity)
            setHasFixedSize(true)
            adapter = ListItemsAdapter {
                startActivity(
                    Intent
                        (
                        this@ListActivity,
                    ItemSpecificationActivity::class.java
                    )
                )
            }
            addItemDecoration(
                    DividerItemDecoration(
                        this@ListActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            val listItems = listOf(
                ItemView(
                    1,
                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                    " کفش عالی",
                    40000,
                    20000,
                    12,
                    OffPrice(50000, 40000)
                ),
                ItemView(
                    2,
                    "http://pluspng.com/img-png/shoes-png-sneaker-png-transparent-image-2500.png",
                    " کفش خوب",
                    10000,
                    30000,
                    12,
                    OffPrice(60000, 45000)
                ),
                ItemView(
                    3,
                    "http://www.pngall.com/wp-content/uploads/2016/03/Shoes-Free-Download-PNG.png",
                    " کفش بی نظیر",
                    40000,
                    20000,
                    12,
                    OffPrice(50000, 40000)
                )
            )
            (adapter as ListItemsAdapter).submitList(listItems)
        }
        cl_list_back_container.setOnClickListener { onBackPressed() }
    }
}
