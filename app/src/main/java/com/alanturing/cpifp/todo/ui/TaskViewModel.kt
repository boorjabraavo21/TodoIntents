package com.alanturing.cpifp.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.model.Task

class TaskViewModel(): ViewModel() {
    private val repository = TaskLocalRepository.getInstance()
    private val _data :MutableLiveData<List<Task>> = MutableLiveData()
    val data: LiveData<List<Task>>
        get() = _data
    init {
        fetchTasks()
    }
    private fun fetchTasks() {
        _data.setValue(repository.tasks)
    }

    fun add(task: Task) {
        repository.add(task)
        val mut = mutableListOf<Task>()
        mut.add(Task(200,"Roto","Rotisimo",false))
        _data.setValue(mut)
        //fetchTasks()

    }

    fun getNextTaskId() = repository.getNextTaskId()
}