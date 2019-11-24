package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
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
        return (oldItem.count == oldItem.count &&
                oldItem.name == newItem.name &&
                oldItem.imageUrl == newItem.imageUrl
                )
    }
}

class CartAdapter : ListAdapter<CartItemView, CartAdapter.ViewHolder>(
    CartRecyclerAdapterCallback
) {
    lateinit var remove: (Int) -> Unit

    inner class ViewHolder(icBinding: ItemCartBinding) : RecyclerView.ViewHolder(icBinding.root) {
        internal val holderBinding = icBinding
        internal val plusImageView: AppCompatImageView =
            itemView.findViewById(R.id.iv_item_cart_plus)

        internal val minusImageView: AppCompatImageView =
            itemView.findViewById(R.id.iv_item_cart_minus)
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

        holder.minusImageView.setOnClickListener {
            if (item.count != 0) {
                item.count--
                holder.holderBinding.cartItemView = item
            } else {
                remove(position)
            }
        }
        holder.plusImageView.setOnClickListener {
            if (item.count < 10) {
                item.count++
                holder.holderBinding.cartItemView = item
            }
        }
    }
}