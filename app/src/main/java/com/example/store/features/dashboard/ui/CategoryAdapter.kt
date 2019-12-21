package com.example.store.features.dashboard.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemCategoryBinding

private object CategoryRecyclerAdapterCallback : DiffUtil.ItemCallback<CategoryView>() {
    override fun areItemsTheSame(oldItem: CategoryView, newItem: CategoryView): Boolean {
        return (oldItem.title == newItem.title)
    }

    override fun areContentsTheSame(oldItem: CategoryView, newItem: CategoryView): Boolean {
        return (oldItem.items == newItem.items)
    }
}

class CategoryAdapter (
    private val itemListener: (Int)->Unit
):
    ListAdapter<CategoryView, CategoryAdapter.ViewHolder>(CategoryRecyclerAdapterCallback) {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCategoryBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.item_category,
                parent,
                false
            )
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.tag = getItem(position)
        holder.holderBinding.categoryView = item
        holder.pieceRecyclerView.setHasFixedSize(true)
        holder.pieceRecyclerView.layoutManager =
            object : LinearLayoutManager(context, HORIZONTAL, true) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.width = width * 38 / 100
                    lp?.setMargins(6, 6, 6, 6)
                    return super.checkLayoutParams(lp)
                }
            }
//            LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true)
        val pieceRecyclerAdapter = PieceRecyclerAdapter(itemListener)
        holder.pieceRecyclerView.adapter = pieceRecyclerAdapter
        pieceRecyclerAdapter.submitList(item.items)
    }

    inner class ViewHolder(cBinding: ItemCategoryBinding) : RecyclerView.ViewHolder(cBinding.root) {
        internal val holderBinding = cBinding
        internal val pieceRecyclerView =
            itemView.findViewById<RecyclerView>(R.id.rv_piece)
    }
}