package com.example.foodiez.ui.product

import com.example.foodiez.domain.product.Product

data class ProductState(
    val product : Product? = null,
    val isLoading : Boolean = false,
    val isRefreshing : Boolean = false,
)
