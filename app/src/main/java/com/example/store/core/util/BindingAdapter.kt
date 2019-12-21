package com.example.store.core.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.store.R
import com.example.store.features.dashboard.ui.OffPrice
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("RoundedCornerImage")
fun loadRoundCornerImage(view: ImageView, imageUrl: String) {
    val radius = 32
    Glide.with(view.context)
        .load(imageUrl)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(radius)))
        .centerInside()
        .placeholder(R.drawable.place_holder)
        .into(view)
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl)
        .centerInside()
        .placeholder(R.drawable.place_holder)
        .into(view)
}

@BindingAdapter("loadTextListWithDivider")
fun loadTextListWithDivider(view: MaterialTextView, colorList: List<String>?) {
    colorList?.let {
        var resultString = ""
        for (colorIndex in 0..colorList.size - 2)
            resultString += "${it[colorIndex]} | "
        resultString += it[colorList.size - 1]
        view.text = resultString
    }
}

@BindingAdapter("hideIfZero")
fun hideIfZero(view: TextView, offPrice: OffPrice) {
    if (offPrice.startOffPrice == 0 || offPrice.endOffPrice == 0)
        view.text = ""
    else view.text = " تا ${offPrice.startOffPrice} تومان ${offPrice.endOffPrice}"
}


