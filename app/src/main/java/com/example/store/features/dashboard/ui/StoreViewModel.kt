package com.example.store.features.dashboard.ui

import androidx.lifecycle.*
import com.example.store.features.dashboard.data.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.store.core.api.Result
import com.example.store.features.dashboard.data.StoreResponse

class StoreViewModel @Inject constructor(val storeRepository: StoreRepository) : ViewModel() {

    private val _storeInfo: MutableLiveData<Result<StoreResponse>?> = MutableLiveData()
    val storeInfo: LiveData<Result<StoreResponse>?>
        get() = _storeInfo

    fun getStoreInfo() {
        viewModelScope.launch {
            _storeInfo.value = storeRepository.getStoreInformation()
        }
    }

}