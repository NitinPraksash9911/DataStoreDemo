package com.example.datastoredemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.datastoredemo.R.layout
import com.example.datastoredemo.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var taskViewModel: TaskViewModel

    lateinit var binding: ActivityMainBinding

    var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layout.activity_main)

        val prefDataStore = PrefDataStore(this)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)


        binding.btn.setOnClickListener {

            taskViewModel.updateTask(binding.nameET.text.toString(), binding.ageET.text.toString())
        }

        taskViewModel.getTask().observe(this) {

            binding.ageTv.text = it.age.toString()
            binding.nameTv.text = it.name
        }

        binding.countBtn.setOnClickListener {
            count++

            lifecycleScope.launch {
                prefDataStore.incrementCounter(count)
            }

        }

        prefDataStore.exampleLiveData.observe(this){

            binding.countTv.text = it.toString()
        }

    }
}