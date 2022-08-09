package com.example.foodiez.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Class that defines the database room.
 */
@Database(
    entities = [],
    version = 1,
    exportSchema = false
)

abstract class FoodiezDatabase : RoomDatabase() {

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
