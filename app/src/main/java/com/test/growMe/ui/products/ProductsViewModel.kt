package com.test.growMe.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.growMe.constants.Constants
import com.test.growMe.data.Product
import com.test.growMe.data.toCartProduct
import com.test.growMe.database.CartRepository
import com.test.growMe.database.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel@Inject constructor(private val repository: ProductRepository, private val cartRepository: CartRepository): ViewModel() {

    //
    private val _updateStatus = MutableStateFlow(Constants.INACTIVE)
    var updateStatus = _updateStatus.asStateFlow()

    /*private val _categoryProducts = MutableStateFlow(emptyList<Product>())
    var categoryProducts = _categoryProducts.asStateFlow()*/

    private val _categoryProducts = MutableStateFlow<List<Product>>(emptyList())
    val categoryProducts: StateFlow<List<Product>> = _categoryProducts

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        fetchProducts()
        refreshProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val data = repository.getProducts()
                _products.value = data
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    private fun refreshProducts() {
        viewModelScope.launch {
            try {
                repository.refreshProductsFromApi()
                _products.value = repository.getProducts()
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    fun setupProductsForCategory(tag: String){
        val list = mutableListOf<Product>()
        for (item in products.value){
            if (item.category == tag){
                list.add(item)
            }
        }
        _categoryProducts.value = list
    }

    fun addToCart(product: Product){
        _updateStatus.value = Constants.LOADING
        viewModelScope.launch {
            try {
                cartRepository.addToCart(product.toCartProduct())
                _updateStatus.value = Constants.SUCCESS
            } catch (e: Exception) {
                _updateStatus.value = Constants.FAILURE
            }
        }
    }

}