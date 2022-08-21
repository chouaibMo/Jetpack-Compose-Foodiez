package com.example.foodiez.ui.product

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.domain.util.Resource
import com.example.foodiez.navigation.ARG_PRODUCT_ID
import com.example.foodiez.navigation.ARG_PRODUCT_SOURCE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository
) : ViewModel() {

    var state by mutableStateOf(ProductState())

    private val productId: Long = checkNotNull(savedStateHandle[ARG_PRODUCT_ID])
    private val productSource: String = checkNotNull(savedStateHandle[ARG_PRODUCT_SOURCE])

    init {
        viewModelScope.launch {
            if (isAlreadyAdded()) {
                productRepository.getProduct(productId.toInt()).collectLatest {
                    state = state.copy(product = it)
                }
            } else {
                productRepository.getRemoteProduct(productId.toString()).collectLatest { result ->
                    state = when (result) {
                        is Resource.Loading -> state.copy(isLoading = true)
                        is Resource.Success -> state.copy(isLoading = false, product = result.data)
                        is Resource.Error -> state.copy(isLoading = false, product = null)
                    }
                }
            }
        }
    }

    fun isAlreadyAdded(): Boolean = productSource == ProductSource.LOCAL.name

    fun saveProduct(product: Product?) {
        viewModelScope.launch {
            product?.let {
                productRepository.saveProduct(product = it)
            }
        }
    }
}