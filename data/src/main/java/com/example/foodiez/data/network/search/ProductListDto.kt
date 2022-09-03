package com.example.foodiez.data.network.search

import com.example.foodiez.data.network.product.ProductData
import com.example.foodiez.domain.product.Product
import com.google.gson.annotations.SerializedName

data class ProductListDto(
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

fun ProductListDto.toProductList(): List<Product> {
    return products
        .filter { !it.productID.isNullOrBlank() }
        .map {
            Product(
                name = it.productName,
                brand = it.brands ?: "",
                imageURL = it.imageURL ?: "",
                fatsPer100 = it.nutriments.fat,
                carbsPer100 = it.nutriments.carbohydrates,
                proteinsPer100 = it.nutriments.proteins,
                caloriePer100 = it.nutriments.energyKcal,
                remoteId = it.productID ?: ""
            )
        }
}