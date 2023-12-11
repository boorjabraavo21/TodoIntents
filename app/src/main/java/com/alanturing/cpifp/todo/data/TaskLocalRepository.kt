package com.alanturing.cpifp.todo.data

import com.alanturing.cpifp.todo.model.Task

class TaskLocalRepository() {
    companion object {
        private var _INSTANCE:TaskLocalRepository? = null
        fun getInstance():TaskLocalRepository {
            if (_INSTANCE == null) {
                _INSTANCE = TaskLocalRepository()
            }
            return _INSTANCE!!
        }
    }
    private val _tasks = mutableListOf<Task>()
    private var contador:Int = 0


    val tasks:List<Task>
        get() = _tasks

    fun add(task:Task) {
        _tasks.add(task)
    }
    fun delete(id:Int) {
        TODO("Código eliminar tarea por id")
    }
    fun update(task:Task) {
       _tasks[_tasks.indexOfFirst { t -> t.id == task.id }] = task
    }

    fun getNextTaskId() = ++contador
}