package com.example.foodiez.ui.product

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.data.utils.Constants
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.navigation.ARG_PRODUCT_ID
import com.example.foodiez.navigation.ARG_PRODUCT_SOURCE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productRepository: ProductRepository
) : ViewModel() {

    var product: StateFlow<Product?> = MutableStateFlow(null)

    private val productId: Long = checkNotNull(savedStateHandle[ARG_PRODUCT_ID])

    private val productSource: String = checkNotNull(savedStateHandle[ARG_PRODUCT_SOURCE])

    init {
        viewModelScope.launch {
            if(isAlreadyAdded()) {
                product = productRepository.getProduct(id = productId.toInt()).stateIn(viewModelScope)
                Log.e(Constants.TAG, "LOCAL : ${product.firstOrNull()?.data?.productName}")
            } else {
                product = productRepository.getProduct(code = productId.toString()).stateIn(viewModelScope)
                Log.e(Constants.TAG, "REMOTE : ${product.firstOrNull()?.data?.productName}")
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