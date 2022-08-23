package com.example.foodiez.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.domain.statistic.Statistic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository, ) : ViewModel() {

    //TODO : stateFlow -> collectAsFlow with lifecycle
    val products: Flow<List<Product>> = flow {
        emitAll(productRepository.getAllProducts())
    }

    var stats by mutableStateOf(Statistic())

    init {
        viewModelScope.launch {
            products.collect {
                stats = stats.apply { computeStats(it) }
            }
        }

    }

    fun removeProduct(product: Product) {
        viewModelScope.launch {
            productRepository.removeProduct(product)
        }
    }
}