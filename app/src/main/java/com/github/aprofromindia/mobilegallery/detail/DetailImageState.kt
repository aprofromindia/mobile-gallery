package com.github.aprofromindia.mobilegallery.detail

import com.github.aprofromindia.mobilegallery.main.Image
import com.github.aprofromindia.redux.Action
import com.github.aprofromindia.redux.State

class SetDetailImage(val image: Image) : Action

class DetailImageState(val image: Image? = null) : State {
    override fun reducer(action: Action): DetailImageState {
        return when (action) {
            is SetDetailImage -> DetailImageState(action.image)
            else -> this
        }
    }
}