package com.github.aprofromindia.mobilegallery.detail

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.github.aprofromindia.mobilegallery.R
import com.github.aprofromindia.mobilegallery.base.BaseActivity
import com.github.aprofromindia.mobilegallery.base.getViewModel
import com.github.aprofromindia.mobilegallery.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity<DetailVM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = getViewModel()
        DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
            .also {
                it.vm = vm
                it.lifecycleOwner = this
            }
    }
}