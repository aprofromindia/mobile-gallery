package com.github.aprofromindia.mobilegallery.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.aprofromindia.mobilegallery.R
import com.github.aprofromindia.mobilegallery.databinding.ItemMainBinding

class MainAdapter(
    var images: List<Image>,
    private val detailDelegate: DetailNavDelegate
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMainBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_main,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = images.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position], detailDelegate)
    }

    class ViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: Image, navDelegate: DetailNavDelegate) {
            binding.image = image
            binding.imageView.setOnClickListener { navDelegate.showDetail(image) }
            binding.executePendingBindings()
        }
    }
}

interface DetailNavDelegate {
    fun showDetail(image: Image)
}