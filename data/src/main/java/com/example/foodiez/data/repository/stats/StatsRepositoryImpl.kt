package com.example.foodiez.data.repository.stats

import com.example.foodiez.data.database.dao.ProductDao
import com.example.foodiez.domain.stats.Stats
import com.example.foodiez.domain.stats.StatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StatsRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : StatsRepository {

    override suspend fun getStats(): Flow<Stats?> {
        return productDao.getAll().map { products ->
            val calories = products.sumOf { it.data.nutriments.energyKcal }.toInt()
            val proteins = products.sumOf { it.data.nutriments.proteins }.toInt()
            val carbs = products.sumOf { it.data.nutriments.carbohydrates }.toInt()
            val fats = products.sumOf { it.data.nutriments.fat }.toInt()
            val stats = Stats(calories, proteins, carbs, fats)

            stats
        }
    }
}