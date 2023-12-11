package com.alanturing.cpifp.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alanturing.cpifp.todo.databinding.TodoItemBinding
import com.alanturing.cpifp.todo.model.Task


class TasksAdapter(private val datos:List<Task>,
                   val onShare:((t:Task,v: View)->Unit),
                   val onEdit:((t:Task,v: View)->Unit)): ListAdapter<Task,TasksAdapter.TaskViewHolder>(DIFF_CALLBACK) {

        object DIFF_CALLBACK: DiffUtil.ItemCallback<Task>() {
            override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

        }


    inner class TaskViewHolder(private val binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindTask(t:Task){
            binding.title.text = t.title
            binding.description.text = t.description
            binding.isCompleted.isChecked = t.isCompleted

            binding.share.setOnClickListener {
                onShare(t, it)
            }
            binding.edit.setOnClickListener {
                onEdit(t, it)
            }
            binding.btnShare.setOnClickListener{
                onShare(t,it)
            }
            binding.btnEdit.setOnClickListener{
                onEdit(t,it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false,)
        return TaskViewHolder(binding)
    }

    override fun getItemCount() = datos.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = datos[position]
        holder.bindTask(task)
    }
}