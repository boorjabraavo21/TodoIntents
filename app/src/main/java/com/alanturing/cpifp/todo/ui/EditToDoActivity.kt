package com.alanturing.cpifp.todo.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.alanturing.cpifp.todo.R
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.databinding.ActivityEditToDoBinding
import com.alanturing.cpifp.todo.model.Task

class EditToDoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditToDoBinding
    private val viewModel:TaskViewModel by viewModels()
    private var task:Task? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditToDoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        task = intent?.extras?.getSerializable("TASK") as Task
        binding.titleInput.setText(task?.title)
        binding.descriptionInput.setText(task?.description)

        binding.topAppBar.setNavigationOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                task?.title = binding.titleInput.text.toString()
                task?.description = binding.descriptionInput.text.toString()
                viewModel.update(task!!)
                setResult(Activity.RESULT_OK)
                finish()
                true
            }
            else -> false
        }
    }
}