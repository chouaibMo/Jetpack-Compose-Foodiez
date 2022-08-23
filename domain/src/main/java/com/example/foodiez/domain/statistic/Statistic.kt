package com.example.foodiez.domain.statistic

import com.example.foodiez.domain.product.Product

data class Statistic(
    var totalFats: Int = 0,
    var totalCarbs: Int = 0,
    var totalProteins: Int = 0,
    var totalCalories: Int = 0,
) {

    fun computeStats(products: List<Product>) {
        totalFats = products.sumOf { it.fatsByQuantity() }.toInt()
        totalCarbs = products.sumOf { it.carbsByQuantity() }.toInt()
        totalProteins = products.sumOf { it.proteinsByQuantity() }.toInt()
        totalCalories = products.sumOf { it.caloriesByQuantity() }.toInt()
    }
}