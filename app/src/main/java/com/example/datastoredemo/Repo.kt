package com.example.datastoredemo

import android.app.Application
import androidx.datastore.DataStore
import androidx.datastore.createDataStore
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repo(context: Application) {

    private val settingsDataStore: DataStore<TaskStore> = context.createDataStore(
            fileName = "settings.pb",
            serializer = TasksSerializer
    )

    private var taskFlow: Flow<Tasks> = settingsDataStore.data.map {

        Tasks(it.name, it.age)
    }

    fun getTask() = taskFlow.asLiveData()

    suspend fun updateTask(name: String, age: Int) {

        settingsDataStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                    .setAge(age)
                    .setName(name)
                    .build()
        }
    }
}