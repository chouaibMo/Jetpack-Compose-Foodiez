package com.example.foodiez.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodiez.domain.product.MealType
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductData
import com.google.gson.annotations.SerializedName

@Entity(tableName = "product")
data class ProductEntity(
    @ColumnInfo(name = "type")  val type: MealType,
    @ColumnInfo(name = "code")  val code: String,
    @ColumnInfo(name = "status")  val status: Int,
    @SerializedName("data") val data : ProductData
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toProduct() = Product(id, type, code, status, data)
}

fun Product.toEntity() : ProductEntity {
    val entity = ProductEntity(type, code, status, data)
    id?.let { entity.id = it }
    return entity
}
