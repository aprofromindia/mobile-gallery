package com.github.aprofromindia.mobilegallery.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.aprofromindia.mobilegallery.R
import com.github.aprofromindia.mobilegallery.base.BaseActivity
import com.github.aprofromindia.mobilegallery.base.getViewModel
import com.github.aprofromindia.mobilegallery.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainVM>(), DetailNavDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = getViewModel()
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .also {
                it.lifecycleOwner = this
                it.vm = vm
            }
        val adapter = MainProvider.adapter(vm.state.value?.images!!, this)
        setupRecyclerView(adapter)
        setupBindings(adapter)
        vm.fetchImages()
    }

    private fun setupRecyclerView(adapter: MainAdapter) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupBindings(adapter: MainAdapter) {
        vm.state.observe(this, Observer { s -> s.ex?.let { showDialog(it) } })
        vm.state.observe(
            this,
            Observer { s ->
                with(adapter) {
                    images = s.images
                    notifyDataSetChanged()
                }
            })
    }

    private fun showDialog(ex: Exception) {
        AlertDialog.Builder(this).create().apply {
            setTitle(R.string.alert_title)
            setMessage(ex.localizedMessage)
            setButton(
                AlertDialog.BUTTON_NEUTRAL,
                getString(R.string.alert_ok)
            ) { dialog, _ -> dialog.dismiss() }
        }.show()
    }

    override fun showDetail(image: Image) {
        router.showDetail(this)
        vm.setDetailState(image)
    }
}
