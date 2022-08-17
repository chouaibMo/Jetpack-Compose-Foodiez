package com.example.foodiez.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodiez.data.database.converters.ProductConverters
import com.example.foodiez.data.database.dao.ProductDao
import com.example.foodiez.data.database.dao.UserDao
import com.example.foodiez.data.database.entity.ProductEntity
import com.example.foodiez.data.database.entity.UserEntity

/**
 * Class that defines the database room.
 */
@Database(
    entities = [UserEntity::class, ProductEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ProductConverters::class)

abstract class FoodiezDatabase : RoomDatabase() {

    /**
     * Dao to access ActivityEntity objects.
     *
     * @return ActivityDao
     */
    abstract fun userProfileDao(): UserDao

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
