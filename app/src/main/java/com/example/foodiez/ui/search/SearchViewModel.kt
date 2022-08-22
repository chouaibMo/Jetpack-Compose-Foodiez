package com.example.foodiez.ui.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.data.utils.Constants.Companion.TAG
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    var productsList: MutableStateFlow<List<Product>> = MutableStateFlow(value = emptyList())

    private var searchJob : Job? = null

    fun searchRequested(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            searchByQuery(query)
        }
    }

    fun searchByQuery(query: String) {
        Log.e(TAG, "searchByQuery: $query", )
        viewModelScope.launch {
            productRepository.searchByQuery(query).collectLatest { result ->
                when (result) {
                    is Resource.Success -> productsList.value = result.data ?: emptyList()
                    is Resource.Error -> productsList.value = emptyList()
                    is Resource.Loading -> {}
                }
            }
        }
    }
}