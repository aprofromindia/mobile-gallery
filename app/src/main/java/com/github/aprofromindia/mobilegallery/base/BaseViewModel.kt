package com.github.aprofromindia.mobilegallery.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.aprofromindia.mobilegallery.AppProvider
import com.github.aprofromindia.redux.State
import com.github.aprofromindia.redux.Subscriber

abstract class BaseViewModel<S> : ViewModel(), Subscriber {
    protected abstract val _state: MutableLiveData<S>
    protected val store = AppProvider.appStore
    val state: LiveData<S> by lazy { _state }

    init {
        @Suppress("LeakingThis")
        store.subscribe(this)
    }

    abstract override fun update(state: State)

    override fun onCleared() {
        store.unsubscribe(this)
        super.onCleared()
    }
}