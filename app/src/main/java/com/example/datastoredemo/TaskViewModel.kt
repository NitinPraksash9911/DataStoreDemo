package com.example.datastoredemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = Repo(application)

    fun getTask() = repo.getTask()

    fun updateTask(name: String, age: String) {

        viewModelScope.launch {
            repo.updateTask(name, age.toInt())

        }

    }

//    @Suppress("UNCHECKED_CAST")
//    class ViewModelFactory(private var application: Application) : ViewModelProvider.Factory {
//
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return TaskViewModel(application) as T
//        }
//
//    }

}