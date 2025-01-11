package com.test.growMe.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.growMe.data.Products
import com.test.growMe.di.ApiService
import com.test.growMe.di.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel@Inject constructor(private val repository: ProductsRepository): ViewModel() {

    private val _newResumeHtmlText = MutableStateFlow("")
    var newResumeHtmlText = _newResumeHtmlText.asStateFlow()

    private val _products = MutableStateFlow<List<Products>>(emptyList())
    val products: StateFlow<List<Products>> = _products

    init {
        fetchProducts()
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

}