package com.alanturing.cpifp.todo.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alanturing.cpifp.todo.R
import com.alanturing.cpifp.todo.adapter.TasksAdapter
import com.alanturing.cpifp.todo.databinding.ActivityMainBinding
import com.alanturing.cpifp.todo.model.Task
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val viewModel:TaskViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    /*private val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        when (it.resultCode) {
            Activity.RESULT_OK -> {
                binding.tasks.adapter = TasksAdapter(viewModel.data.value!!, ::onShareItem, ::onEditItem)
            }
            Activity.RESULT_CANCELED -> {
                Snackbar.make(this,binding.root,"Se ha cancelado",Snackbar.LENGTH_SHORT).show()
            }
        }
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)
        binding.tasks.adapter = TasksAdapter(viewModel.data.value!!, ::onShareItem, ::onEditItem)
        val taskObserver = Observer<List<Task>>{}
        viewModel.data.observe(this,taskObserver)
        binding.createTodo.setOnClickListener {
            val createIntent = Intent(this, CreateToDoActivity::class.java)
            // getResult.launch(createIntent)
            startActivity(createIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tasks.adapter = TasksAdapter(viewModel.data.value!!, ::onShareItem, ::onEditItem)
    }

    fun onShareItem(task:Task, view:View) {
        val statusText = if (task.isCompleted) R.string.status_completada.toString()
                        else R.string.status_pendiente.toString()
        val shareText = getString(R.string.share_text, task.title, task.description, statusText)
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    fun onEditItem(task:Task, view:View) {
        val editIntent = Intent(this, EditToDoActivity::class.java)
        editIntent.putExtra("TASK",task)
        startActivity(editIntent)
    }
}