package com.example.foodiez.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    var productsList: MutableStateFlow<List<Product>> = MutableStateFlow(value = emptyList())

    init {
        viewModelScope.launch {
            productRepository.searchByQuery("whey").collectLatest { result ->
                when (result) {
                    is Resource.Success -> productsList.value = result.data ?: emptyList()
                    is Resource.Error -> productsList.value = emptyList()
                    is Resource.Loading -> {}
                }
            }
        }
    }
}