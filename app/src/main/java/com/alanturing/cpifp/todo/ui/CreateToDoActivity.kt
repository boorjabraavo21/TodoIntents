package com.alanturing.cpifp.todo.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityCreateToDoBinding
import com.alanturing.cpifp.todo.model.Task

class CreateToDoActivity : FormActivity() {
    private lateinit var binding: ActivityCreateToDoBinding
    //private val repository = TaskLocalRepository.getInstance()
    private val viewModel:TaskViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityCreateToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        binding.topAppBar.setNavigationOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    override fun onSave(): Boolean {
        val task = Task(viewModel.getNextTaskId(),
            binding.titleInput.text.toString(),
            binding.descriptionInput.text.toString(),
            false)
        viewModel.add(task)
        finish()
        return true
    }
}