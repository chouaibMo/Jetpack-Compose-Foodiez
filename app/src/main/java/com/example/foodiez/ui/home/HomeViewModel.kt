package com.example.foodiez.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.domain.stats.Stats
import com.example.foodiez.domain.stats.StatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val statsRepository: StatsRepository,
) : ViewModel() {

    //TODO : stateFlow -> collectAsFlow with lifecycle
    val products: Flow<List<Product>> = flow {
        emitAll(productRepository.getAllProducts())
    }

    lateinit var stats: Flow<Stats?>

    init {
        viewModelScope.launch {
            stats = statsRepository.getStats()
        }
    }
}