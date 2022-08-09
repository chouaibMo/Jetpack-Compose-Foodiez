package com.example.foodiez.domain.product

import com.google.gson.annotations.SerializedName

data class Product (
    @SerializedName("code")
    val code: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("status_verbose")
    val statusVerbose: String,

    @SerializedName("product")
    val data : ProductData
)