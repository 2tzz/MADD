package com.example.lab_4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private var todos: MutableList<Todo>,
    private val onDeleteClick: (Todo) -> Unit,
    private val onEditClick: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTodoTitle: TextView = itemView.findViewById(R.id.tvTodoTitle)
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDelete)
        val btnEdit: ImageButton = itemView.findViewById(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]
        holder.tvTodoTitle.text = todo.title

        holder.btnDelete.setOnClickListener {
            onDeleteClick(todo)
        }

        holder.btnEdit.setOnClickListener {
            onEditClick(todo)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}