package com.example.store.features.dashboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.store.features.dashboard.data.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.store.core.api.Result
import com.example.store.features.dashboard.data.StoreResponse

class StoreViewModel @Inject constructor(val storeRepository: StoreRepository) : ViewModel() {

    val storeInfo = liveData {
        val data = storeRepository.getStoreInformation()
        emit(data)
    }

}