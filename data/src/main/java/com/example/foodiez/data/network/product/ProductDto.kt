package com.example.foodiez.data.network.product

import com.example.foodiez.domain.product.Product
import com.google.gson.annotations.SerializedName

data class ProductDto(

    @SerializedName("code")
    val code: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("product")
    val data: ProductData,
)


fun ProductDto.toProduct(): Product =
    Product(
        remoteId = data.productID,
        name = data.productName,
        brand = data.brands ?: "",
        imageURL = data.imageURL ?: "",
        fatsPer100 = data.nutriments.fat,
        carbsPer100 = data.nutriments.carbohydrates,
        proteinsPer100 = data.nutriments.proteins,
        caloriePer100 = data.nutriments.energyKcal,
    )

