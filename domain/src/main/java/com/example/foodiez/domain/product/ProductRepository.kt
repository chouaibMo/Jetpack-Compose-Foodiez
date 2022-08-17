package com.example.foodiez.domain.product

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepository {

    suspend fun saveProduct(product: Product)

    suspend fun removeProduct(product: Product)

    suspend fun getProduct(code: String): Flow<Product?>

    suspend fun getProduct(id: Int): Flow<Product?>

    suspend fun getAllProducts(): Flow<List<Product>>

    suspend fun searchByQuery(query: String): Response<ProductList>
}