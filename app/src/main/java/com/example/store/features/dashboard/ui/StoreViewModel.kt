package com.example.store.features.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.MainActivity
import com.example.store.features.dashboard.data.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.store.core.api.Result
import com.example.store.features.dashboard.data.StoreResponse

class StoreViewModel : ViewModel() {

    @Inject lateinit var storeRepository: StoreRepository

    init {
        MainActivity.daggerStoreComponent.inject(this)
    }

    fun getStoreInformation() {
        var result: Result<StoreResponse>? = null
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                result
            }
        }
    }
}