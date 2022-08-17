package com.example.foodiez.data.repository.product

import com.example.foodiez.data.database.dao.ProductDao
import com.example.foodiez.data.database.entity.toEntity
import com.example.foodiez.data.network.ProductApi
import com.example.foodiez.data.network.SearchApi
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductList
import com.example.foodiez.domain.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productDao: ProductDao,
    private val productApi: ProductApi,
    private val searchApi: SearchApi
) : ProductRepository {

    override suspend fun saveProduct(product: Product) {
        productDao.insert(product.toEntity())
    }

    override suspend fun removeProduct(product: Product) {
        productDao.remove(product.toEntity())
    }

    override suspend fun getProduct(code: String): Flow<Product?> {
        val response = productApi.getProduct(code)
        return if (response.isSuccessful) {
            response.body()?.let {
                flowOf(it)
            } ?: run {
                flowOf(null)
            }
        } else {
            flowOf(null)
        }
    }

    override suspend fun getProduct(id: Int): Flow<Product?> =
        productDao.get(id).map { it?.toProduct() }

    override suspend fun getAllProducts(): Flow<List<Product>> =
        productDao.getAll().map {
            it.map { entity ->
                entity.toProduct()
            }
        }

    override suspend fun searchByQuery(query: String): Response<ProductList> {
        return searchApi.searchByQuery(query)
    }

}