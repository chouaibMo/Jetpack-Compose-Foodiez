package com.example.foodiez.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.domain.statistic.Statistic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    //TODO : stateFlow -> collectAsFlow with lifecycle
    val products: Flow<List<Product>> = flow {
        emitAll(productRepository.getAllProducts())
    }

    var stats = MutableStateFlow(Statistic())

    init {
        viewModelScope.launch {
            products.collect { list ->
                stats.value = stats.value.copy(
                    list.sumOf { it.fatsByQuantity() }.toInt(),
                    list.sumOf { it.carbsByQuantity() }.toInt(),
                    list.sumOf { it.proteinsByQuantity() }.toInt(),
                    list.sumOf { it.caloriesByQuantity() }.toInt(),
                )
            }
        }
    }

    fun removeProduct(product: Product) {
        viewModelScope.launch {
            productRepository.removeProduct(product)
        }
    }
}