package com.alanturing.cpifp.todo

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityEditItemBinding
import com.alanturing.cpifp.todo.model.Task

class EditItemActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditItemBinding
    private val repository:TaskLocalRepository = TaskLocalRepository.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val task:Task? = intent?.extras?.getParcelable("com.alanturing.cpifp.todo.TASK")
        if (task!=null) {
            binding.titleInput.setText(task.title)
            binding.descriptionInput.setText(task.description)
            binding.statusSwitch.isChecked = task.isCompleted
        }
        binding.cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        binding.updateButton.setOnClickListener {
            task?.title = binding.titleInput.text.toString()
            task?.description = binding.descriptionInput.text.toString()
            task?.isCompleted = binding.statusSwitch.isChecked
            repository.update(task!!)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}