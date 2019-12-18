package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.store.R
import kotlinx.android.synthetic.main.activity_order_success.*


class OrderSuccessActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)
        overridePendingTransition(R.anim.right_in, R.anim.left_out)

        cl_order_success_back_container.setOnClickListener {
            overridePendingTransition(R.anim.left_in, R.anim.right_out)
            this.onBackPressed()
        }

        btn_followup.setOnClickListener {
            overridePendingTransition(R.anim.left_in, R.anim.right_out)
            startActivity(
                Intent(
                    this,
                    PaymentHistory::class.java
                )
            )
        }

        btn_back_to_homepage.setOnClickListener {
            overridePendingTransition(R.anim.left_in, R.anim.right_out)
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                ).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_in, R.anim.right_out)
    }
}
