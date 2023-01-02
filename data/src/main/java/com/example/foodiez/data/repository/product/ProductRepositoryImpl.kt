package com.example.foodiez.data.repository.product

import com.example.foodiez.data.database.dao.ProductDao
import com.example.foodiez.data.database.entity.toEntity
import com.example.foodiez.data.network.product.ProductApi
import com.example.foodiez.data.network.product.toProduct
import com.example.foodiez.data.network.search.SearchApi
import com.example.foodiez.data.network.search.toProductList
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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

    override suspend fun getProduct(id: Int): Flow<Product?> =
        productDao.get(id).map { it?.toProduct() }

    override suspend fun getAllProducts(): Flow<List<Product>> =
        productDao.getAll().map {
            it.map { entity -> entity.toProduct() }
        }

    override suspend fun getRemoteProduct(code: String): Flow<Resource<Product>> {
        return flow {
            emit(Resource.Loading<Product>())
            try {
                val response = productApi.getProduct(code)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(Resource.Success<Product>(data = it.toProduct()))
                    } ?: run {
                        emit(Resource.Error<Product>(message = "unable to get product $code"))
                    }
                } else {
                    emit(Resource.Error<Product>(message = "unable to get product $code"))
                }
            } catch (e : Exception) {
                emit(Resource.Error<Product>(message = "unable to get product $code"))
            }
        }
    }

    override suspend fun searchByQuery(query: String): Flow<Resource<List<Product>>> {
        return flow {
            emit(Resource.Loading<List<Product>>())
            try {

                val response = searchApi.searchByQuery(query)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val result = it.toProductList()

                        emit(Resource.Success<List<Product>>(data = result))
                    } ?: run {
                        emit(Resource.Error<List<Product>>(message = "unable to search with $query"))
                    }
                } else {
                    emit(Resource.Error<List<Product>>(message = "unable to search with $query"))
                }
            } catch (e : Exception) {
                emit(Resource.Error<List<Product>>(message = "unable to search with $query"))
            }
        }
    }
}