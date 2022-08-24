package com.example.foodiez.data.di

import android.content.Context
import com.example.foodiez.data.datastore.PreferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {


    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context) = PreferencesDataStore(context)
}