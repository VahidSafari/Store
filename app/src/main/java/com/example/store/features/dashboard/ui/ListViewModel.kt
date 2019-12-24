package com.example.store.features.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.features.dashboard.data.ListEntity
import com.example.store.features.dashboard.data.ListRepository
import com.example.store.features.dashboard.data.ParentChildEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(private val listRepository: ListRepository) : ViewModel() {

    private val _listItems: MutableLiveData<List<ListView>?> = MutableLiveData()
    val listItems: LiveData<List<ListView>?>
        get() = _listItems

    fun getListItems(parentId: Int) = viewModelScope.launch {
        _listItems.value = listRepository.getListChildren(parentId)
    }

    fun insertListItems(listItems: List<ListView>) = viewModelScope.launch {
        listRepository.insertListItems(listItems)
    }

    fun insertParentChildPair(parentId: Int, childId: Int) = viewModelScope.launch {
        listRepository.insertParentChildPair(ParentChildEntity(parentId, childId))
    }

}