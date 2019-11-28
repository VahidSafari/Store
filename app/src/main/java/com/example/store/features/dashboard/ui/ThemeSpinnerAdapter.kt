package com.example.store.features.dashboard.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.store.R
import kotlinx.android.synthetic.main.theme_spinner_item.view.*

class ThemeSpinnerAdapter(
    private val themeList: List<ThemeView>,
    val context: Context
) : BaseAdapter() {
    override fun getView(i: Int, p1: View?, viewGroup: ViewGroup?): View {
        return if (p1 == null) {
            val view = LayoutInflater.from(context).inflate(R.layout.theme_spinner_item, null)
            view.tv_spinner_theme_name.text = themeList[i].name
            view.iv_spinner_image.setImageResource(themeList[i].icon)
            view
        } else p1
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int = themeList.count()
}