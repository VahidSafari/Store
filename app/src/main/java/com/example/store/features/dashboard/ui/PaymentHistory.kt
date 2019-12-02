package com.example.store.features.dashboard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.R
import kotlinx.android.synthetic.main.activity_payment_history.*

class PaymentHistory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_history)

        cl_history_back_container.setOnClickListener {
            onBackPressed()
        }

        rv_history.apply {
            layoutManager = LinearLayoutManager(this@PaymentHistory)
            setHasFixedSize(true)
            val factorAdapter = FactorAdapter()
            adapter = factorAdapter
            factorAdapter.submitList(
                listOf(
                    FactorView(
                        1,
                        "۲ کفش خوب و ۳ کفش عالی",
                        200000,
                        ProductState.DELIVERED
                    ),
                    FactorView(
                        2,
                        "۴ دفتر و ۳ کتاب",
                        1200000,
                        ProductState.DELIVERED
                    ),
                    FactorView(
                        3,
                        "۵ کاپشن و ۳ شلوار",
                        1435000,
                        ProductState.DELIVERED
                    )
                )
            )
        }
    }
}
