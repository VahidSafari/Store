package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemCartBinding

private object CartRecyclerAdapterCallback : DiffUtil.ItemCallback<CartItemView>() {
    override fun areItemsTheSame(oldItem: CartItemView, newItem: CartItemView): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: CartItemView, newItem: CartItemView): Boolean {
        return (oldItem.count == oldItem.count)
    }

}

class CartAdapter :
    ListAdapter<CartItemView, CartAdapter.ViewHolder>(
        CartRecyclerAdapterCallback
    ) {

    inner class ViewHolder(icBinding: ItemCartBinding) : RecyclerView.ViewHolder(icBinding.root) {
        internal val holderBinding = icBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCartBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_cart, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.tag = getItem(position)
        holder.holderBinding.cartItemView = item
    }
}