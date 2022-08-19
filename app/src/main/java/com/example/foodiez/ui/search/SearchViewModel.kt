package com.example.foodiez.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.domain.product.ProductList
import com.example.foodiez.domain.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    var productsList by mutableStateOf<ProductList?>(value = null)

    init {
        viewModelScope.launch {
            val response = productRepository.searchByQuery("whey")
            if (response.isSuccessful) {
                response.body()?.let {
                    productsList = it
                }
            }
        }
    }
}