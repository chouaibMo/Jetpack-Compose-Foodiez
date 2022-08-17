package com.example.foodiez.data.di

import android.content.Context
import androidx.room.Room
import com.example.foodiez.data.database.FoodiezDatabase
import com.example.foodiez.data.database.dao.ProductDao
import com.example.foodiez.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Object that groups the singletons of database.
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    /**
     * Creation of the Database instance.
     * In DEBUG version the database is not encrypted, otherwise we use android-database-sqlcipher
     * @see <a href="https://github.com/sqlcipher/android-database-sqlcipher">android-database-sqlcipher</a>
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FoodiezDatabase {
        return Room.databaseBuilder(
            context,
            FoodiezDatabase::class.java,
            FoodiezDatabase.DATABASE_NAME
        ).build()
    }

    /**
     * Creation of the UserDao instance.
     *
     * @param database instance of a database
     * @return UserDao Dao of the table 'user'
     */
    @Singleton
    @Provides
    fun provideUserDao(database: FoodiezDatabase): UserDao = database.userProfileDao()


    @Singleton
    @Provides
    fun provideProductDao(database: FoodiezDatabase): ProductDao = database.productDao()
}