package com.example.foodiez.domain.product

import com.google.gson.annotations.SerializedName

data class Product (

    val id : Int?,

    var type : MealType,

    @SerializedName("code")
    val code: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("product")
    val data : ProductData,
)

enum class MealType(val tag: String) {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    SNACK("Snack"),
    DINER("Diner")
}