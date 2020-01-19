package com.github.aprofromindia.mobilegallery.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.github.aprofromindia.mobilegallery.AppProvider
import com.github.aprofromindia.mobilegallery.base.BaseViewModel
import com.github.aprofromindia.mobilegallery.detail.SetDetailImage
import com.github.aprofromindia.mobilegallery.rest.ImageService
import com.github.aprofromindia.redux.State
import com.github.aprofromindia.redux.Store
import com.github.aprofromindia.redux.Thunk
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainVM(private val imageService: ImageService = AppProvider.imageService) :
    BaseViewModel<ImagesState>() {
    override val _state = MutableLiveData(store.imagesState)
    private val exceptionHandler =
        CoroutineExceptionHandler { _, ex -> store.dispatch(FailedImages(Exception(ex.localizedMessage))) }

    fun fetchImages() {
        store.dispatch(object : Thunk {
            override fun invoke(store: Store) {
                store.dispatch(FetchImages())
                viewModelScope.launch(exceptionHandler) {
                    val response = imageService.getImages()
                    if (response.isSuccessful)
                        store.dispatch(SuccessImages(response.body()?.images!!))
                    else throw Exception(response.message())
                }
            }
        })
    }

    override fun update(state: State) {
        if (state is ImagesState && _state.value !== state) {
            _state.value = state
        }
    }

    fun setDetailState(image: Image) {
        store.dispatch(SetDetailImage(image))
    }
}