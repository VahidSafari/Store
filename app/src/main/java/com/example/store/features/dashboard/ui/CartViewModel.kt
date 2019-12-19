package com.example.store.features.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.store.features.dashboard.data.StoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(private val storeRepository: StoreRepository) :
    ViewModel() {

    private val _cartItems: MutableLiveData<List<CartItemView>?> = MutableLiveData()
    val cartItems: LiveData<List<CartItemView>?>
        get() = _cartItems

    fun getCartItems() = viewModelScope.launch(Dispatchers.IO) {
        _cartItems.value = storeRepository.getCartItems()
    }

    fun insertCartItem(pieceId: Int, categoryId: Int) = viewModelScope.launch(Dispatchers.IO) {
        storeRepository.insertCartItem(pieceId, categoryId)
    }

    fun removeCartItem(pieceId: Int, categoryId: Int) = viewModelScope.launch(Dispatchers.IO) {
        storeRepository.insertCartItem(pieceId, categoryId)
    }

}