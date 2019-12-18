package com.example.store.features.dashboard.ui

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.store.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var storeViewModelFactory: ViewModelProvider.Factory
    private val storeViewModel: StoreViewModel by viewModels {
        storeViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sv_search_product.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showInputMethod(v.findFocus())
            }
        }

        val searchTextView = sv_search_product.findViewById<TextView>(R.id.search_src_text)
        searchTextView.typeface =
            Typeface.createFromAsset(activity?.assets, "fonts/iran_yekan_regular.ttf")
        sv_search_product.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (query.isNotEmpty())
                        storeViewModel.search("%$query%")
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (newText.isNotEmpty())
                        storeViewModel.search("%$newText%")
                }
                return false
            }
        })

        val listItemAdapter = ListItemsAdapter {
            startActivity(
                Intent(
                    activity,
                    ItemSpecificationActivity::class.java
                )
            )
        }
        rv_product_search_results.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listItemAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        storeViewModel.searchResult.observe(viewLifecycleOwner, Observer { pieceList ->
            if (pieceList == null || pieceList.isEmpty()) {
                tv_search_result.visibility = View.VISIBLE
                rv_product_search_results.visibility = View.GONE
            } else if (pieceList.isNotEmpty()) {
                tv_search_result.visibility = View.GONE
                rv_product_search_results.visibility = View.VISIBLE
                listItemAdapter.submitList(pieceList.map { it.toItemView() })
            }
        })
    }

    private fun showInputMethod(view: View) {
        (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    fun showKeyboard() {
        sv_search_product.requestFocus()
        if (Build.VERSION.SDK_INT <= 22)
            showInputMethod(sv_search_product)
    }
}
