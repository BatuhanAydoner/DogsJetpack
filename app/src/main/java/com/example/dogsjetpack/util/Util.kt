package com.example.dogsjetpack.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dogsjetpack.R

// Method for imageView
infix fun ImageView.setDogImage(url: String?) {
    var option = RequestOptions()
        .placeholder(R.drawable.dog_icon)
        .error(R.drawable.dog_icon)

    Glide.with(this.context).setDefaultRequestOptions(option).load(url).into(this)
}

// Attribute method
@BindingAdapter("android:imageUrl")
fun imageUrl(v: ImageView, url: String?) {
    v.setDogImage(url)
}