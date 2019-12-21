package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemPreviewBinding

private object ItemRecyclerAdapterCallback : DiffUtil.ItemCallback<PreviewView>() {
    override fun areItemsTheSame(oldItem: PreviewView, newItem: PreviewView): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: PreviewView, newItem: PreviewView): Boolean {
        return (oldItem.imageUrl == newItem.imageUrl)
    }
}

class ItemSpecificationAdapter
    : ListAdapter<PreviewView, ItemSpecificationAdapter.ViewHolder>(ItemRecyclerAdapterCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemPreviewBinding: ItemPreviewBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_preview,
                parent,
                false
            )
        return ViewHolder(itemPreviewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.holderBinding.preview = getItem(position)
    }

    inner class ViewHolder(iBinding: ItemPreviewBinding) : RecyclerView.ViewHolder(iBinding.root) {
        internal val holderBinding = iBinding
    }

}