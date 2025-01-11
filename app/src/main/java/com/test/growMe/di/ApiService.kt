package com.test.growMe.di

import com.test.growMe.data.Products
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Products>
}