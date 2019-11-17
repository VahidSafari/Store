package com.example.store.features.dashboard.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.*
import com.example.store.R
import kotlinx.coroutines.*

private object HeaderRecyclerAdapterCallback : DiffUtil.ItemCallback<StoreView>() {
    override fun areItemsTheSame(oldItem: StoreView, newItem: StoreView): Boolean {
        return (oldItem.hashCode() == newItem.hashCode())
    }

    override fun areContentsTheSame(oldItem: StoreView, newItem: StoreView): Boolean {
        return (oldItem.categoryViewList == oldItem.categoryViewList &&
                oldItem.categoryViewList == oldItem.topSliderViewList)
    }
}

class HeaderAdapter(
    private val viewLifecycleOwner: LifecycleOwner
) : ListAdapter<StoreView, RecyclerView.ViewHolder>(HeaderRecyclerAdapterCallback) {
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
        if (holder is HeaderRecyclerViewHolder)
            holder.topSliderAdapter.submitList(getItem(0).topSliderViewList)
        else if (holder is CategoryRecyclerViewHolder)
            holder.categoryAdapter.submitList(getItem(0).categoryViewList)
    }

    inner class HeaderRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val headerRecyclerView = view.findViewById<RecyclerView>(R.id.rv_top_slider)
        internal val topSliderAdapter = TopSliderAdapter()

        init {
            headerRecyclerView.layoutManager =
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
            headerRecyclerView.adapter = topSliderAdapter
            headerRecyclerView.setHasFixedSize(true)
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(headerRecyclerView)
            autoScrollTopSlider(headerRecyclerView)
        }
    }

    inner class CategoryRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val categoryRecyclerView = view.findViewById<RecyclerView>(R.id.rv_categories)
        internal val categoryAdapter = CategoryAdapter()

        init {
            categoryRecyclerView.layoutManager =
                LinearLayoutManager(context)

            categoryRecyclerView.adapter = categoryAdapter
            categoryRecyclerView.setHasFixedSize(true)
        }
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
                var postScrollPosition: Int
                topSliderRecyclerView.let {
                    postScrollPosition =
                        (topSliderRecyclerView.layoutManager as LinearLayoutManager)
                            .findFirstCompletelyVisibleItemPosition()
                }
                if (postScrollPosition != preScrollPosition)
                    continue
                topSliderRecyclerView.let {
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