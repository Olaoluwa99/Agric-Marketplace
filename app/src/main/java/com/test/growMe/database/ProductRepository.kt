package com.test.growMe.database

import com.test.growMe.data.Product
import com.test.growMe.data.toProduct
import com.test.growMe.di.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao
) {
    suspend fun getProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    suspend fun assignProducts(cartProduct: List<Product>) {
        productDao.assignProducts(cartProduct)
    }

    suspend fun refreshProductsFromApi() {
        val productsFromApi = apiService.getProducts()
        val finalProductsList = mutableListOf<Product>()
        for (apiProduct in productsFromApi){
            finalProductsList.add(apiProduct.toProduct())
        }
        productDao.assignProducts(finalProductsList)
    }
}