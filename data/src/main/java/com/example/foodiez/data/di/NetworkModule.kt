package com.example.foodiez.data.di

import com.example.foodiez.data.network.product.ProductApi
import com.example.foodiez.data.network.search.SearchApi
import com.example.foodiez.data.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("product_url")
    fun provideProductEndpoint() = Constants.OFF_PRODUCT_ENDPOINT

    @Provides
    @Named("search_url")
    fun provideSearchEndpoint() = Constants.OFF_SEARCH_ENDPOINT

    @Singleton
    @Provides
    fun provideOkHttpClient() =
        OkHttpClient
            .Builder()
            .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.READ_WRITE_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    @Named("product_retrofit")
    fun provideProductRetrofit(client: OkHttpClient, @Named("product_url") url: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(client)
            .build()

    @Singleton
    @Provides
    @Named("search_retrofit")
    fun provideSearchRetrofit(client: OkHttpClient, @Named("search_url") url: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(client)
            .build()

    @Provides
    @Singleton
    fun provideProductApi(@Named("product_retrofit") retrofit: Retrofit): ProductApi =
        retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideSearchApi(@Named("search_retrofit") retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)

}
