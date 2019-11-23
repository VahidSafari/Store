package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemPieceBinding

private object PieceRecyclerAdapterCallback : DiffUtil.ItemCallback<ItemView>() {
    override fun areItemsTheSame(oldItem: ItemView, newItem: ItemView): Boolean {
        return (oldItem.id == newItem.id)
    }

    override fun areContentsTheSame(oldItem: ItemView, newItem: ItemView): Boolean {
        return (oldItem.name == newItem.name)
    }
}

class PieceRecyclerAdapter(
    val itemListener: ()->Unit
):
ListAdapter<ItemView,PieceRecyclerAdapter.ViewHolder>(PieceRecyclerAdapterCallback){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding : ItemPieceBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_piece,
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

    inner class ViewHolder(pBinding : ItemPieceBinding) : RecyclerView.ViewHolder(pBinding.root){
        internal val holderBinding = pBinding
    }
}