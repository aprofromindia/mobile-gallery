package com.github.aprofromindia.mobilegallery.main

object MainProvider {
    fun adapter(images: List<Image>, detailDelegate: DetailNavDelegate) =
        MainAdapter(images, detailDelegate)
}