package com.test.growMe.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.growMe.data.CartProduct
import com.test.growMe.database.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val repository: CartRepository) : ViewModel() {

    //
    //
    private val _cartProducts = MutableStateFlow<List<CartProduct>>(emptyList())
    val products: StateFlow<List<CartProduct>> = _cartProducts

    init {
        fetchCartProducts()
    }

    private fun fetchCartProducts() {
        viewModelScope.launch {
            try {
                val data = repository.getCartProducts()
                _cartProducts.value = data
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

}