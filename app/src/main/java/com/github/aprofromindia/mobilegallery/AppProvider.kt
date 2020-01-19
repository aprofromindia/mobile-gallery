package com.github.aprofromindia.mobilegallery

import com.github.aprofromindia.mobilegallery.detail.DetailImageState
import com.github.aprofromindia.mobilegallery.main.ImagesState
import com.github.aprofromindia.mobilegallery.redux.AppStore
import com.github.aprofromindia.redux.ThunkMiddleWare
import com.github.aprofromindia.mobilegallery.rest.ImageLoader
import com.github.aprofromindia.mobilegallery.rest.ImageService
import com.github.aprofromindia.mobilegallery.rest.RestClient

object AppProvider {
    val router = AppRouter()
    val imageService: ImageService = RestClient().retrofit.create(ImageService::class.java)
    val appStore = AppStore(
        mapOf(
            AppStore.IMAGES_STATE to ImagesState(),
            AppStore.DETAIL_IMAGE_STATE to DetailImageState()
        )
    ).apply { addMiddleware(com.github.aprofromindia.redux.ThunkMiddleWare()) }

    val imageLoader = ImageLoader()
}