package com.example.store.features.dashboard.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import com.example.store.databinding.ItemPreviewBinding


class ItemSpecificationAdapter(
    private val previewImageList: List<PreviewView>
) : RecyclerView.Adapter<ItemSpecificationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBindingUtil: ItemPreviewBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.,
                parent,
                false
            )
        return ViewHolder(dataBindingUtil)

    }

    override fun getItemCount() = previewImageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.holderBinding.preview = previewImageList[position]
    }

    inner class ViewHolder(iBinding: ItemPreviewBinding) : RecyclerView.ViewHolder(iBinding.root) {
        internal val holderBinding = iBinding
    }
}