package com.thsai.jetpack.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.thsai.jetpack.R

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    imageUrl?.apply {
        Glide.with(view.context)
            .asBitmap()
            .load(imageUrl)
            .placeholder(R.drawable.glide_placeholder)
            .centerCrop()
            .into(view)
    }
}