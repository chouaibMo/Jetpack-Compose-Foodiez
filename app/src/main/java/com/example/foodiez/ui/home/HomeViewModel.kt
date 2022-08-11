package com.example.foodiez.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.data.utils.Constants.Companion.TAG
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductRepository
import com.example.foodiez.domain.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    //TODO : stateFlow -> collectAsFlow
    var product by mutableStateOf<Product?>(value = null)

    init {
        viewModelScope.launch {
            val response = productRepository.getProduct("3046920022651")
            if (response.isSuccessful) {
                response.body()?.let {
                    product = it
                }
            }
        }
    }
}