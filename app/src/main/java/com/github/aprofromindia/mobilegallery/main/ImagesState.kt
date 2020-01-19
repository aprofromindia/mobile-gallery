package com.github.aprofromindia.mobilegallery.main

import com.github.aprofromindia.redux.Action
import com.github.aprofromindia.redux.State

class FetchImages : Action

class SuccessImages(val images: List<Image>) : Action

class FailedImages(val ex: Exception) : Action

class ImagesState(
    val isLoading: Boolean = false,
    val images: List<Image> = emptyList(),
    val ex: Exception? = null
) : State {
    override fun reducer(action: Action): ImagesState {
        return when (action) {
            is FetchImages -> ImagesState(true, emptyList(), null)
            is SuccessImages -> ImagesState(false, action.images, null)
            is FailedImages -> ImagesState(false, emptyList(), action.ex)
            else -> this
        }
    }
}
