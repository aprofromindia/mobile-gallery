package com.github.aprofromindia.mobilegallery.detail

import androidx.lifecycle.MutableLiveData
import com.github.aprofromindia.mobilegallery.base.BaseViewModel
import com.github.aprofromindia.redux.State

class DetailVM : BaseViewModel<DetailImageState>() {
    override val _state = MutableLiveData(store.detailImageState)

    override fun update(state: State) {
        if (state is DetailImageState) {
            _state.value = state
        }
    }
}
