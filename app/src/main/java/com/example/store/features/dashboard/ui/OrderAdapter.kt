package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemProductBinding

private object OrderRecyclerAdapterCallback : DiffUtil.ItemCallback<ProductView>() {
    override fun areItemsTheSame(oldItem: ProductView, newItem: ProductView): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: ProductView, newItem: ProductView): Boolean {
        return (oldItem.count == oldItem.count)
    }

}

class OrderAdapter :
    ListAdapter<ProductView, OrderAdapter.ViewHolder>(
        OrderRecyclerAdapterCallback
    ) {

    inner class ViewHolder(icBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(icBinding.root) {
        internal val holderBinding = icBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemProductBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_product, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.tag = getItem(position)
        holder.holderBinding.productView = item
    }
}