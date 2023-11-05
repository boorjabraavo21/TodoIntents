package com.alanturing.cpifp.todo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityEditToDoBinding
import com.alanturing.cpifp.todo.model.Task

class EditToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditToDoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = TaskLocalRepository.getInstance()
        binding = ActivityEditToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val task: Task = intent?.extras?.getSerializable("TASK") as Task
        binding.titleInput.setText(task.title)
        binding.descriptionInput.setText(task.description)
        binding.saveButton.setOnClickListener {
            repository.update(Task(task.id, binding.titleInput.toString(),
                binding.descriptionInput.toString(), task.isCompleted))
            setResult(Activity.RESULT_OK)
            finish()
        }
        binding.cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}