package com.example.store.core.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.store.R
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
