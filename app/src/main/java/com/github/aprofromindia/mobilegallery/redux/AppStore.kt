package com.github.aprofromindia.mobilegallery.redux

import com.github.aprofromindia.mobilegallery.detail.DetailImageState
import com.github.aprofromindia.mobilegallery.main.ImagesState
import com.github.aprofromindia.redux.State
import com.github.aprofromindia.redux.Store

class AppStore(state: Map<String, State>) : Store(state) {
    val imagesState: ImagesState
        get() = state[IMAGES_STATE] as ImagesState
    val detailImageState: DetailImageState
        get() = state[DETAIL_IMAGE_STATE] as DetailImageState

    companion object {
        val IMAGES_STATE = AppStore::imagesState.name
        val DETAIL_IMAGE_STATE = AppStore::detailImageState.name
    }
}