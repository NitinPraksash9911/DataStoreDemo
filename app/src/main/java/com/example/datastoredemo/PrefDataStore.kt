package com.example.datastoredemo

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PrefDataStore(context: Context) {

    private val dataStore: DataStore<androidx.datastore.preferences.Preferences> = context.createDataStore(
            name = "settings"
    )

    private val EXAMPLE_COUNTER = preferencesKey<Int>("example_counter")

    private val exampleCounterFlow: Flow<Int> = dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[EXAMPLE_COUNTER] ?: 0
            }

    val exampleLiveData = exampleCounterFlow.asLiveData()

    suspend fun incrementCounter(int: Int) {
        dataStore.edit { settings ->
            val currentCounterValue = settings[EXAMPLE_COUNTER] ?: 0
            settings[EXAMPLE_COUNTER] = int
        }
    }

}