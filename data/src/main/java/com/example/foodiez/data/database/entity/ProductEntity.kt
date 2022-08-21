package com.example.foodiez.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodiez.domain.product.MealType
import com.example.foodiez.domain.product.Product

@Entity(tableName = "product")
data class ProductEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "type") val type: MealType?,
    @ColumnInfo(name = "imageURL") val imageURL: String,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "fatsPer100") val fatsPer100: Double,
    @ColumnInfo(name = "carbsPer100") val carbsPer100: Double,
    @ColumnInfo(name = "proteinsPer100") val proteinsPer100: Double,
    @ColumnInfo(name = "caloriePer100") val caloriePer100: Double,
    @ColumnInfo(name = "remoteId") val remoteId: String,

    ) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun toProduct() =
        Product(
            id = id,
            name = name,
            brand = brand,
            type = type,
            imageURL = imageURL,
            quantity = quantity,
            fatsPer100 = fatsPer100,
            carbsPer100 = carbsPer100,
            proteinsPer100 = proteinsPer100,
            caloriePer100 = caloriePer100,
            remoteId = remoteId,
        )
}

fun Product.toEntity(): ProductEntity {
    val entity = ProductEntity(
        name = name,
        type = type,
        brand = brand,
        imageURL = imageURL,
        quantity = quantity,
        fatsPer100 = fatsPer100,
        carbsPer100 = carbsPer100,
        proteinsPer100 = proteinsPer100,
        caloriePer100 = caloriePer100,
        remoteId = remoteId,
    )
    id?.let { entity.id = it }
    return entity
}




