package com.github.aprofromindia.mobilegallery.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.github.aprofromindia.mobilegallery.AppProvider

@BindingAdapter("app:srcUrl")
fun setImageURl(
    imageView: ImageView,
    url: String
) {
    val imageLoader = AppProvider.imageLoader
    imageLoader.load(imageView.context, url, imageView)
}