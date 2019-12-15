package com.example.store.features.user


import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.store.R
import com.example.store.features.dashboard.ui.PaymentHistory
import com.example.store.features.dashboard.ui.ThemeSpinnerAdapter
import com.example.store.features.dashboard.ui.ThemeView
import kotlinx.android.synthetic.main.dialog_agreement.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        val params = sp_theme.layoutParams
        params.width = metrics.widthPixels / 2
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        sp_theme.requestLayout()
        sp_theme.setSelection(0)

        cl_support.setOnClickListener {
            lifecycleScope.launch {
                Toast.makeText(
                    context,
                    " در حال باز کردن سایت پشتیبانی ",
                    Toast.LENGTH_SHORT
                ).show()
                delay(600)
                val uri: Uri = Uri.parse("http://www.google.com")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
        }

        cl_agreement.setOnClickListener {
            context?.let {
                val agreementDialog = Dialog(it)
                agreementDialog.apply {
                    setContentView(R.layout.dialog_agreement)
                    btn_agreement.setOnClickListener {
                        dismiss()
                    }
                    show()
                }
            }
        }

        cl_history.setOnClickListener {
            startActivity(Intent(activity,PaymentHistory::class.java))
        }

        nsv_profile.isNestedScrollingEnabled = true

        val themeList = listOf(
            ThemeView(1, "Light", R.drawable.ic_sun),
            ThemeView(2, "Dark", R.drawable.ic_moon),
            ThemeView(3, "Custom", R.drawable.ic_pencil)
        )
        context?.let {
            val themeAdapter = ThemeSpinnerAdapter(themeList, it)
            sp_theme.adapter = themeAdapter
        }
        sp_theme.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                //TODO: Change theme
            }
        }
    }
}
