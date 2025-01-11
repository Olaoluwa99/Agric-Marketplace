package com.test.growMe.di

import com.test.growMe.data.Products
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductsRepository {
    override suspend fun getProducts(): List<Products> {
        return apiService.getProducts()
    }
}