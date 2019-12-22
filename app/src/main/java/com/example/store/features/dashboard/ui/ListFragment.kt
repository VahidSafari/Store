package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_list.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val listAdapter = ListRecyclerAdapter(
            { startActivity(Intent(activity, ListActivity::class.java)) },
            {}
        )
        rv_list.adapter = listAdapter
        rv_list.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        val listItems = mutableListOf(
            ListView(
                1,
                "مواد غذایی",
                ListItemType.NESTED
            ),
            ListView(
                2,
                "کالای دیجیتال",
                ListItemType.FLATTEN
            ),
            ListView(
                3,
                "لوازم تحریر",
                ListItemType.FLATTEN
            ),
            ListView(
                4,
                "مد و پوشاک",
                ListItemType.NESTED
            ),
            ListView(
                5,
                "ورزش و سفر",
                ListItemType.FLATTEN
            )
        )
        listAdapter.submitList(listItems)
    }

    fun newInstance(list: List<ListView>): ListFragment {
        val listItems = mutableListOf(
            ListView(
                1,
                "مواد غذایی",
                ListItemType.NESTED
            ),
            ListView(
                2,
                "کالای دیجیتال",
                ListItemType.FLATTEN
            ),
            ListView(
                3,
                "لوازم تحریر",
                ListItemType.FLATTEN
            ),
            ListView(
                4,
                "مد و پوشاک",
                ListItemType.NESTED
            ),
            ListView(
                5,
                "ورزش و سفر",
                ListItemType.FLATTEN
            )
        )
        return ListFragment()
    }

}
