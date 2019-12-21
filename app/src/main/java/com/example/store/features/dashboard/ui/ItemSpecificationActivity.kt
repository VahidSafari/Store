package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.store.R
import com.example.store.databinding.ActivityItemSpecificationBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_item_specification.*
import javax.inject.Inject

class ItemSpecificationActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var storeViewModelFactory: ViewModelProvider.Factory
    private val cartViewModel: CartViewModel by viewModels {
        storeViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pieceId = intent.getIntExtra("pieceId", -1)
        val binding: ActivityItemSpecificationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_item_specification)
        rv_item_specification.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rv_item_specification.setHasFixedSize(true)
        val itemSpecificationAdapter = ItemSpecificationAdapter()
        rv_item_specification.adapter = itemSpecificationAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_item_specification)
        cartViewModel.getPiece(pieceId)
        cartViewModel.piece.observe(this, Observer {
            it?.let { item ->
                binding.itemSpecificationView =
                    ItemSpecificationView(
                        item.id,
                        item.name,
                        item.startPrice,
                        item.endPrice,
                        item.offPrice.startOffPrice,
                        item.offPrice.endOffPrice,
                        listOf("قهوه ای", "صورتی", "سفید"),
                        38,
                        43
                    )
                itemSpecificationAdapter.submitList(listOf(PreviewView(1, item.imageUrl)))
            }
        })

        btn_add_to_cart.setOnClickListener {
            cartViewModel.insertCartItem(pieceId).invokeOnCompletion {
                startActivity(Intent(this, CartActivity::class.java))
            }
        }
        cl_back_container_item_specification.setOnClickListener {
            onBackPressed()
        }
    }
}