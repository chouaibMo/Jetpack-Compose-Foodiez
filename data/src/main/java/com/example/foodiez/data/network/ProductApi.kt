package com.example.foodiez.data.network

import com.example.foodiez.domain.product.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("product/{code}.json")
    suspend fun getProduct(@Path("code") code: String): Response<Product>
}