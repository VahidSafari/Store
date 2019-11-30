package com.example.store.features.dashboard.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
        val listAdapter = ListRecyclerAdapter {
            startActivity(Intent(activity,ListActivity::class.java))
        }
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
                "مواد"
            ),
            ListView(
                2,
                "مواد مناسب"
            ),
            ListView(
                3,
                "مواد نامناسب"
            ),
            ListView(
                4,
                "خودرو"
            ),
            ListView(
                5,
                "اسباب بازی"
            )
        )
        listAdapter.submitList(listItems)
    }
}
