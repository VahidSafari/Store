package com.example.store.features.dashboard.ui

import androidx.lifecycle.*
import com.example.store.features.dashboard.data.StoreRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.store.core.api.Result
import com.example.store.features.dashboard.data.PieceEntity
import com.example.store.features.dashboard.data.PiecesDto
import com.example.store.features.dashboard.data.TopSliderEntity

class StoreViewModel @Inject constructor(private val storeRepository: StoreRepository) :
    ViewModel() {

    private val _storeInfo: MutableLiveData<Result<Pair<List<TopSliderEntity>, List<PiecesDto>>>?> =
        MutableLiveData()
    val storeInfo: LiveData<Result<Pair<List<TopSliderEntity>, List<PiecesDto>>>?>
        get() = _storeInfo

    private val _searchResult: MutableLiveData<List<PieceEntity>?> = MutableLiveData()
    val searchResult: LiveData<List<PieceEntity>?>
        get() = _searchResult

    fun getStoreInfo() {
        viewModelScope.launch {
            _storeInfo.value = storeRepository.getStoreInfo()
        }
    }

    fun search(searchPhrase: String) {
        viewModelScope.launch {
            _searchResult.value = storeRepository.search(searchPhrase)
        }
    }

    fun populateDataBase() = viewModelScope.launch {
        storeRepository.populateDataBase()
    }

}