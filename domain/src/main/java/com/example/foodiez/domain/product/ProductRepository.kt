package com.example.foodiez.domain.product

import retrofit2.Response

interface ProductRepository {

    suspend fun getProduct(code: String): Response<Product>

    suspend fun searchByQuery(query: String): Response<ProductList>
}