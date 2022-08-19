package com.example.foodiez.domain.stats

import kotlinx.coroutines.flow.Flow

interface StatsRepository {

    suspend fun getStats() : Flow<Stats?>
}