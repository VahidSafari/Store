package com.example.store.features.dashboard.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.store.R
import kotlinx.coroutines.*

class HeaderAdapter(
    private val storeView: StoreView,
    private val viewLifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var headerRecyclerView: RecyclerView
    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        context = parent.context
        return if (viewType == HeaderAdapterItemType.TYPE_HEADER_RECYCLER.type) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_top_slider, parent, false)
            headerRecyclerView = view.findViewById(R.id.rv_top_slider)
            HeaderRecyclerViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_item_categories, parent, false)
            categoryRecyclerView = view.findViewById(R.id.rv_categories)
            CategoryRecyclerViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeaderRecyclerViewHolder) {
            holder.headerRecyclerView.layoutManager =
                object : LinearLayoutManager(
                    context,
                    HORIZONTAL,
                    true
                ) {
                    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                        lp?.width = width * 85 / 100
                        lp?.setMargins(12, 12, 12, 12)
                        return super.checkLayoutParams(lp)
                    }
                }
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(holder.headerRecyclerView)
            val topSliderAdapter = TopSliderAdapter()
            holder.headerRecyclerView.adapter = topSliderAdapter
            topSliderAdapter.submitList(storeView.topSliderViewList)
            holder.headerRecyclerView.setHasFixedSize(true)
            autoScrollTopSlider(holder.headerRecyclerView)
        } else if (holder is CategoryRecyclerViewHolder) {
            holder.categoryRecyclerView.layoutManager =
                LinearLayoutManager(context)
            val categoryAdapter = CategoryAdapter()
            holder.categoryRecyclerView.adapter = categoryAdapter
            holder.categoryRecyclerView.setHasFixedSize(true)
            categoryAdapter.submitList(storeView.categoryViewList)
        }
    }

    inner class HeaderRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val headerRecyclerView = view.findViewById<RecyclerView>(R.id.rv_top_slider)
    }

    inner class CategoryRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val categoryRecyclerView = view.findViewById<RecyclerView>(R.id.rv_categories)
    }

    override fun getItemViewType(position: Int) =
        if (position == 0)
            HeaderAdapterItemType.TYPE_HEADER_RECYCLER.type
        else
            HeaderAdapterItemType.TYPE_CATEGORY_RECYCLER.type

    private fun autoScrollTopSlider(topSliderRecyclerView: RecyclerView) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main, CoroutineStart.DEFAULT) {
            while (isActive) {
                val preScrollPosition =
                    (topSliderRecyclerView.layoutManager as LinearLayoutManager)
                        .findFirstCompletelyVisibleItemPosition()
                delay(3000)
                var postScrollPosition = -1
                topSliderRecyclerView?.let {
                    postScrollPosition =
                        (topSliderRecyclerView.layoutManager as LinearLayoutManager)
                            .findFirstCompletelyVisibleItemPosition()
                }
                if (postScrollPosition != preScrollPosition)
                    continue
                topSliderRecyclerView?.let {
                    when (postScrollPosition) {
                        topSliderRecyclerView.adapter?.itemCount?.minus(1) -> {
                            topSliderRecyclerView.smoothScrollToPosition(0)
                        }
                        else -> {
                            topSliderRecyclerView.smoothScrollToPosition(++postScrollPosition)
                        }
                    }
                }
            }
        }
    }

}