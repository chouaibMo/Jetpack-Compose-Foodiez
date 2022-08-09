package com.example.foodiez.domain.product

import com.google.gson.annotations.SerializedName

data class Nutriscore (
    val energy: Long,

    @SerializedName("energy_points")
    val energyPoints: Long,

    @SerializedName("energy_value")
    val energyValue: Long,

    val fiber: Long,

    @SerializedName("fiber_points")
    val fiberPoints: Long,

    @SerializedName("fiber_value")
    val fiberValue: Long,

    val grade: String,

    @SerializedName("is_beverage")
    val isBeverage: Long,

    @SerializedName("is_cheese")
    val isCheese: Long,

    @SerializedName("is_fat")
    val isFat: Long,

    @SerializedName("is_water")
    val isWater: Long,

    @SerializedName("negative_points")
    val negativePoints: Long,

    @SerializedName("positive_points")
    val positivePoints: Long,

    val proteins: Long,

    @SerializedName("proteins_points")
    val proteinsPoints: Long,

    @SerializedName("proteins_value")
    val proteinsValue: Long,

    @SerializedName("saturated_fat")
    val saturatedFat: Long,

    @SerializedName("saturated_fat_points")
    val saturatedFatPoints: Long,

    @SerializedName("saturated_fat_ratio")
    val saturatedFatRatio: Long,

    @SerializedName("saturated_fat_ratio_points")
    val saturatedFatRatioPoints: Long,

    @SerializedName("saturated_fat_ratio_value")
    val saturatedFatRatioValue: Long,

    @SerializedName("saturated_fat_value")
    val saturatedFatValue: Long,

    val score: Long,
    val sodium: Double,

    @SerializedName("sodium_points")
    val sodiumPoints: Long,

    @SerializedName("sodium_value")
    val sodiumValue: Double,

    val sugars: Double,

    @SerializedName("sugars_points")
    val sugarsPoints: Long,

    @SerializedName("sugars_value")
    val sugarsValue: Double
)