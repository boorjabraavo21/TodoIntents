package com.alanturing.cpifp.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alanturing.cpifp.todo.data.TaskLocalRepository
import com.alanturing.cpifp.todo.model.Task

class TaskViewModel(): ViewModel() {
    private val repository = TaskLocalRepository.getInstance()
    private val _data:MutableLiveData<List<Task>> = MutableLiveData()
    val data:LiveData<List<Task>>
        get() = _data

    init {
        fetchTasks()
    }

    fun add(task: Task) {
        repository.add(task)
        fetchTasks()
    }

    fun update(task: Task) {
        repository.update(task)
        fetchTasks()
    }

    fun getNextTaskId() = repository.getNextTaskId()

    fun fetchTasks() {
        _data.value = repository.tasks
    }
}