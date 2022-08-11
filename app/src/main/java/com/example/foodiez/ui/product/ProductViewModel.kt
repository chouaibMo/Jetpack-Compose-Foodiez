package com.example.foodiez.ui.product

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodiez.data.utils.Constants
import com.example.foodiez.domain.product.Product
import com.example.foodiez.domain.product.ProductList
import com.example.foodiez.domain.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val productRepository: ProductRepository) : ViewModel() {

    var product by mutableStateOf<Product?>(value = null)
    var productsList by mutableStateOf<ProductList?>(value = null)

    init {
//        viewModelScope.launch {
//            val response = productRepository.getProduct("3229820021027")
//            if (response.isSuccessful) {
//                response.body()?.let {
//                    product = it
//                }
//            }
//        }
        viewModelScope.launch {
            val response = productRepository.searchByQuery("banana")
            if (response.isSuccessful) {
                Log.e(Constants.TAG, "searchByQuery : isSuccessful ", )
                response.body()?.let {
                    Log.e(Constants.TAG, "searchByQuery : ${response.body()}", )
                    productsList = it
                }
            } else {
                Log.e(Constants.TAG, "searchByQuery : failed ", )
            }
        }
    }
}