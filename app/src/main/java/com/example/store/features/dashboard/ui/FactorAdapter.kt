package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemFactorBinding

private object FactorAdapterCallback : DiffUtil.ItemCallback<FactorView>() {
    override fun areItemsTheSame(oldItem: FactorView, newItem: FactorView): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: FactorView, newItem: FactorView): Boolean {
        return (oldItem.totalPrice == newItem.totalPrice &&
                oldItem.state == newItem.state &&
                oldItem.productList == newItem.productList)
    }
}

class FactorAdapter : ListAdapter<FactorView,FactorAdapter.ViewHolder>(FactorAdapterCallback) {
    inner class ViewHolder(
        itemFactorBinding: ItemFactorBinding
    ): RecyclerView.ViewHolder(itemFactorBinding.root){
        internal val holderBinding = itemFactorBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemFactorBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_factor, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.tag = item
        holder.holderBinding.factorView = item
    }
}