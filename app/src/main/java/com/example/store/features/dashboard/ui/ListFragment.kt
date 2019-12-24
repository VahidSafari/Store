package com.example.store.features.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.store.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class ListFragment : DaggerFragment() {

    @Inject
    lateinit var storeViewModelFactory: ViewModelProvider.Factory
    private val listViewModel: ListViewModel by viewModels {
        storeViewModelFactory
    }

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
            {  },
            {  }
        )
        rv_list.adapter = listAdapter
        rv_list.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        arguments?.let {
            listViewModel.getListItems(it.getInt(argumentKeyName))
        }
        listViewModel.listItems.observe(this, Observer {
            listAdapter.submitList(it)
        })
    }

    companion object {

        const val argumentKeyName: String = "parentId"

        fun newInstance(parentId: Int): ListFragment {
            val args = Bundle()
            args.putInt("parentId",parentId)
            val listFragment = ListFragment()
            listFragment.arguments = args
            return listFragment
        }

    }

}
