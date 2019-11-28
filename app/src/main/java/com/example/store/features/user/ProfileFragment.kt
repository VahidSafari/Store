package com.example.store.features.user


import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.example.store.R
import com.example.store.features.dashboard.ui.ThemeSpinnerAdapter
import com.example.store.features.dashboard.ui.ThemeView
import kotlinx.android.synthetic.main.fragment_profile.*


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
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                when(parent?.getItemAtPosition(position).toString()){
                }
            }

        }
    }
}
