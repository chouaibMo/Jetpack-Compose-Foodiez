package com.example.foodiez.data.repository.product

import com.example.foodiez.data.network.ProductApi
import com.example.foodiez.data.network.SearchApi
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductList
import com.example.foodiez.domain.product.ProductRepository
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val searchApi: SearchApi
) :
    ProductRepository {

    override suspend fun getProduct(code: String): Response<Product> {
        return productApi.getProduct(code)
    }

    override suspend fun searchByQuery(query: String): Response<ProductList> {
        return searchApi.searchByQuery(query)
    }

}