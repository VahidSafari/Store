package com.example.store.features.dashboard.ui

import androidx.lifecycle.*
import com.example.store.features.dashboard.data.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.store.core.api.Result
import com.example.store.features.dashboard.data.PiecesDto
import com.example.store.features.dashboard.data.StoreResponse
import com.example.store.features.dashboard.data.TopSliderEntity

class StoreViewModel @Inject constructor(private val storeRepository: StoreRepository) : ViewModel() {

    private val _storeInfo: MutableLiveData<Result<Pair<List<TopSliderEntity>, List<PiecesDto>>>?> = MutableLiveData()
    val storeInfo: LiveData<Result<Pair<List<TopSliderEntity>,List<PiecesDto>>>?>
        get() = _storeInfo

    fun getStoreInfo() {
        viewModelScope.launch {
            _storeInfo.value = storeRepository.getStoreInfo()
        }
    }

}