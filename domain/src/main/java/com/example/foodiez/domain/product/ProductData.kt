package com.example.foodiez.domain.product

import com.google.gson.annotations.SerializedName

data class ProductData(

    @SerializedName("_keywords")
    val keywords: List<String>,

    @SerializedName("additives_n")
    val additivesN: Long,

    @SerializedName("additives_tags")
    val additivesTags: List<String>,

    @SerializedName("compared_to_category")
    val comparedToCategory: String,

    val complete: Long,
    val completeness: Double,

    val creator: String,

    @SerializedName("food_groups")
    val foodGroups: String,

    @SerializedName("generic_name")
    val genericName: String,

    @SerializedName("generic_name_en")
    val genericNameEn: String,

    @SerializedName("id")
    val productID: String,

    @SerializedName("image_url")
    val imageURL: String,

    @SerializedName("brands")
    val brands: String,

    val nutriments: Nutriments,
//
//    @SerializedName("nutriscore_data")
//    val nutriscore: Nutriscore,

    @SerializedName("nutriscore_grade")
    val nutriscoreGrade: String,

    @SerializedName("nutriscore_score")
    val nutriscoreScore: Long,

    @SerializedName("product_name")
    val productName: String,

    @SerializedName("serving_quantity")
    val servingQuantity: String,

    @SerializedName("serving_size")
    val servingSize: String,
)