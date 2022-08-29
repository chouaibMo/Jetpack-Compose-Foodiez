package com.example.foodiez.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodiez.data.database.converters.ProductConverters
import com.example.foodiez.data.database.dao.ProductDao
import com.example.foodiez.data.database.entity.ProductEntity

/**
 * Class that defines the database room.
 */
@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProductConverters::class)

abstract class FoodiezDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        /**
         * Name of the database.
         */
        const val DATABASE_NAME: String = "database"

        /**
         * Key of the database passphrase.
         */
        const val PASSPHRASE_KEY = "DataBaseModulePassphrase"

        /**
         * Length of the database passphrase.
         */
        const val PASSPHRASE_LENGTH = 256
    }
}
