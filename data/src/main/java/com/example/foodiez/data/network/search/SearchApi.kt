package com.example.foodiez.data.network.search

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search.pl")
    suspend fun searchByQuery(
        @Query("search_terms") query: String,
        @Query("page") page: String = "1",
        @Query("json") format: String = "1"
    ): Response<ProductListDto>
}