package com.test.growMe.di

import com.test.growMe.data.Products

interface ProductsRepository {
    suspend fun getProducts(): List<Products>
}