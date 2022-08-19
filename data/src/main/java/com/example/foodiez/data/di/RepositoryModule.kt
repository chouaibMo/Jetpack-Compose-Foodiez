package com.example.foodiez.data.di

import com.example.foodiez.data.repository.product.ProductRepositoryImpl
import com.example.foodiez.data.repository.stats.StatsRepositoryImpl
import com.example.foodiez.data.repository.user.UserRepositoryImpl
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.domain.stats.StatsRepository
import com.example.foodiez.domain.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @ViewModelScoped
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    @ViewModelScoped
    abstract fun bindStatsRepository(statsRepositoryImpl: StatsRepositoryImpl): StatsRepository
}