package com.example.store.core.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.store.R
import com.example.store.features.dashboard.ui.OffPrice
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso


@BindingAdapter("RoundedCornerImage")
fun loadRoundCornerImage(view: ImageView, imageUrl: String) {
    val radius = 32
    val margin = 0
    val transformation = RoundedCornerTransformation(radius,margin)
    Picasso.get()
        .load(imageUrl)
        .transform(transformation)
        .fit()
        .centerInside()
        .placeholder(R.drawable.place_holder)
        .into(view)
}

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .fit()
        .centerInside()
        .placeholder(R.drawable.place_holder)
        .into(view)
}

@BindingAdapter("loadTextListWithDivider")
fun loadTextListWithDivider(view: MaterialTextView, colorList: List<String>){
    var resultString = ""
    for(colorIndex in 0..colorList.size-2)
        resultString += "${colorList[colorIndex]} | "
    resultString += colorList[colorList.size-1]
    view.text = resultString
}

@BindingAdapter("hideIfZero")
fun hideIfZero(view:TextView, offPrice: OffPrice) {
    if (offPrice.startOffPrice == 0 || offPrice.endOffPrice == 0)
        view.text = ""
    else view.text = " تا ${offPrice.startOffPrice} تومان ${offPrice.endOffPrice}"
}


