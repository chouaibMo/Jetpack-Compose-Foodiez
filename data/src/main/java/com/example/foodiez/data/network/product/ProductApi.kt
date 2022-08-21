package com.example.foodiez.data.network.product

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

// TODO : use response with failure, success and loading

interface ProductApi {

    @GET("product/{code}.json")
    suspend fun getProduct(@Path("code") code: String): Response<ProductDto>
}