package com.example.foodiez.data.database.converters

import androidx.room.TypeConverter
import com.example.foodiez.data.network.product.Nutriments
import com.example.foodiez.domain.product.Product
import com.example.foodiez.data.network.product.ProductData
import com.google.gson.Gson

class ProductConverters {

    @TypeConverter
    fun gsonToProduct(json: String?): Product? {
        return Gson().fromJson(json, Product::class.java)
    }

    @TypeConverter
    fun ProductToGson(product: Product): String? {
        return Gson().toJson(product)
    }

    @TypeConverter
    fun gsonToProductData(json: String?): ProductData? {
        return Gson().fromJson(json, ProductData::class.java)
    }

    @TypeConverter
    fun ProductDataToGson(data: ProductData): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun gsonToNutriments(json: String?): Nutriments? {
        return Gson().fromJson(json, Nutriments::class.java)
    }

    @TypeConverter
    fun NutrimentsToGson(nutriments: Nutriments): String? {
        return Gson().toJson(nutriments)
    }
}