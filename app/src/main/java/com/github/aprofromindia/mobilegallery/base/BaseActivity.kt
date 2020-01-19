package com.github.aprofromindia.mobilegallery.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.github.aprofromindia.mobilegallery.AppProvider

abstract class BaseActivity<T : BaseViewModel<*>> : AppCompatActivity() {
    protected lateinit var vm: T
    protected val router = AppProvider.router
}

inline fun <reified T : BaseViewModel<*>> BaseActivity<T>.getViewModel() =
    ViewModelProviders.of(this).get(T::class.java)