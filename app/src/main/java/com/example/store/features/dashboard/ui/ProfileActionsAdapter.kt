package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemProfileActionBinding


private object ProfileRecyclerAdapterCallback : DiffUtil.ItemCallback<ListView>() {
    override fun areItemsTheSame(oldItem: ListView, newItem: ListView): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: ListView, newItem: ListView): Boolean {
        return (oldItem.title == newItem.title)
    }
}

class ProfileActionsAdapter (
    val itemListener: () -> Unit
): ListAdapter<ListView, ProfileActionsAdapter.ViewHolder>(
    ProfileRecyclerAdapterCallback
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemProfileActionBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_profile_action, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.lBinding.listView = item
        holder.itemView.setOnClickListener {
            itemListener()
        }
    }

    inner class ViewHolder(internal val lBinding: ItemProfileActionBinding):
        RecyclerView.ViewHolder(lBinding.root)
}