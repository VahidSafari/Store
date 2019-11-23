package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemSliderBinding


private object TopSliderRecyclerAdapterCallback : DiffUtil.ItemCallback<TopSliderView>() {
    override fun areItemsTheSame(oldItem: TopSliderView, newItem: TopSliderView): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: TopSliderView, newItem: TopSliderView): Boolean {
        return (oldItem.imageUrl == newItem.imageUrl)
    }
}

class TopSliderAdapter :
    ListAdapter<TopSliderView, TopSliderAdapter.ViewHolder>(TopSliderRecyclerAdapterCallback) {
    inner class ViewHolder(iBinding: ItemSliderBinding) : RecyclerView.ViewHolder(iBinding.root) {
        val holderBinding = iBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemSliderBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_slider,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.tag = getItem(position)
        holder.holderBinding.topSliderView = item
    }
}