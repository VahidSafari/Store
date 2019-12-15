package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemCartBinding
import com.example.store.databinding.ItemListItemsBinding


private object ListItemRecyclerAdapterCallback : DiffUtil.ItemCallback<ItemView>() {
    override fun areItemsTheSame(oldItem: ItemView, newItem: ItemView): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: ItemView, newItem: ItemView): Boolean {
        return (oldItem.name == newItem.name &&
                oldItem.startPrice == newItem.startPrice &&
                oldItem.endPrice == oldItem.endPrice &&
                oldItem.offPrice.startOffPrice == newItem.offPrice.startOffPrice && 
                oldItem.offPrice.endOffPrice == newItem.offPrice.endOffPrice
                )
    }
}

class ListItemsAdapter(
    val itemListener: () -> Unit
) : ListAdapter<ItemView, ListItemsAdapter.ViewHolder>(
    ListItemRecyclerAdapterCallback
) {

    inner class ViewHolder(
        ilBinding: ItemListItemsBinding
    ) : RecyclerView.ViewHolder(ilBinding.root) {
        internal val holderBinding = ilBinding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemListItemsBinding =
            DataBindingUtil.inflate(
                inflater, 
                R.layout.item_list_items, 
                parent, 
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.tag = getItem(position)
        holder.holderBinding.itemView = item
        holder.itemView.setOnClickListener {
            itemListener()
        }
    }
}