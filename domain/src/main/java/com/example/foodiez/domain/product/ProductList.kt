package com.example.foodiez.domain.product

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("count")
    val count: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("page_count")
    val pageCount: Int,

    @SerializedName("page_size")
    val pageSize: Int,

    @SerializedName("products")
    val products: List<ProductData>,
)