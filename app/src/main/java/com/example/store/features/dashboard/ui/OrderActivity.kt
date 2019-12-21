package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_order.*
import javax.inject.Inject

class OrderActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var storeViewModelFactory: ViewModelProvider.Factory
    private val cartViewModel: CartViewModel by viewModels {
        storeViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        overridePendingTransition(R.anim.right_in, R.anim.left_out)


        cl_order_back_container.setOnClickListener {
            this.onBackPressed()
        }

        btn_order.setOnClickListener {
            startActivity(Intent(this, OrderSuccessActivity::class.java))
        }

        rv_order.layoutManager = LinearLayoutManager(this)
        val orderAdapter = OrderAdapter()
        rv_order.adapter = orderAdapter
        rv_order.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        cartViewModel.getCartItems()
        cartViewModel.cartItems.observe(this, Observer {
            it?.let {
                orderAdapter.submitList(it.map { cartItemView -> cartItemView.toProductView() })
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_in, R.anim.right_out)
    }
}
