package com.example.foodiez.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore("foodiez_datastore")


class PreferencesDataStore @Inject constructor(@ApplicationContext private val context: Context) {

    private val dataStore = context.dataStore

    suspend fun saveValue(key: String, value: String) {
        val wrappedKey = stringPreferencesKey(key)
        dataStore.edit {
            it[wrappedKey] = value
        }

    }

    suspend fun saveValue(key: String, value: Int) {
        val wrappedKey = intPreferencesKey(key)
        dataStore.edit {
            it[wrappedKey] = value
        }

    }

    suspend fun saveValue(key: String, value: Double) {
        val wrappedKey = doublePreferencesKey(key)
        dataStore.edit {
            it[wrappedKey] = value
        }

    }

    suspend fun saveValue(key: String, value: Long) {
        val wrappedKey = longPreferencesKey(key)
        dataStore.edit {
            it[wrappedKey] = value
        }

    }

    suspend fun saveValue(key: String, value: Boolean) {
        val wrappedKey = booleanPreferencesKey(key)
        dataStore.edit {
            it[wrappedKey] = value
        }

    }

    suspend fun getStringValue(key: String, default: String = ""): String {
        val wrappedKey = stringPreferencesKey(key)
        val valueFlow: Flow<String> = dataStore.data.map {
            it[wrappedKey] ?: default
        }
        return valueFlow.first()
    }

    suspend fun getIntValue(key: String, default: Int = 0): Int {
        val wrappedKey = intPreferencesKey(key)
        val valueFlow: Flow<Int> = dataStore.data.map {
            it[wrappedKey] ?: default
        }
        return valueFlow.first()
    }

    suspend fun getDoubleValue(key: String, default: Double = 0.0): Double {
        val wrappedKey = doublePreferencesKey(key)
        val valueFlow: Flow<Double> = dataStore.data.map {
            it[wrappedKey] ?: default
        }
        return valueFlow.first()
    }

    suspend fun getLongValue(key: String, default: Long = 0L): Long {
        val wrappedKey = longPreferencesKey(key)
        val valueFlow: Flow<Long> = context.dataStore.data.map {
            it[wrappedKey] ?: default
        }
        return valueFlow.first()
    }

    suspend fun getBooleanValue(key: String, default: Boolean = false): Boolean {
        val wrappedKey = booleanPreferencesKey(key)
        val valueFlow: Flow<Boolean> = dataStore.data.map {
            it[wrappedKey] ?: default
        }
        return valueFlow.first()
    }

}
