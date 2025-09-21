package com.example.lab_4

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvTodoList: RecyclerView
    private lateinit var btnAddTodo: Button
    private lateinit var todoAdapter: TodoAdapter
    private var todoList = mutableListOf<Todo>()
    private var nextId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        rvTodoList = findViewById(R.id.rvTodoList)
        btnAddTodo = findViewById(R.id.btnAddTodo)

        // Setup RecyclerView
        rvTodoList.layoutManager = LinearLayoutManager(this)
        todoAdapter = TodoAdapter(
            todoList,
            onDeleteClick = { todo -> deleteTodo(todo) },
            onEditClick = { todo -> showEditDialog(todo) }
        )
        rvTodoList.adapter = todoAdapter

        // Add button click listener
        btnAddTodo.setOnClickListener {
            showAddDialog()
        }
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_todo, null)
        val etTodo = dialogView.findViewById<EditText>(R.id.etTodo)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Add Todo")
            .create()

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnSave.setOnClickListener {
            val title = etTodo.text.toString().trim()
            if (title.isNotEmpty()) {
                addTodo(Todo(nextId++, title))
                dialog.dismiss()
            } else {
                etTodo.error = "Please enter a todo item"
            }
        }

        dialog.show()
    }

    private fun showEditDialog(todo: Todo) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_todo, null)
        val etTodo = dialogView.findViewById<EditText>(R.id.etTodo)
        val btnCancel = dialogView.findViewById<Button>(R.id.btnCancel)
        val btnSave = dialogView.findViewById<Button>(R.id.btnSave)

        etTodo.setText(todo.title)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle("Edit Todo")
            .create()

        btnCancel.setOnClickListener {
            dialog.dismiss()
        }

        btnSave.setOnClickListener {
            val title = etTodo.text.toString().trim()
            if (title.isNotEmpty()) {
                todo.title = title
                todoAdapter.notifyDataSetChanged()
                dialog.dismiss()
            } else {
                etTodo.error = "Please enter a todo item"
            }
        }

        dialog.show()
    }

    private fun addTodo(todo: Todo) {
        todoList.add(todo)
        todoAdapter.notifyItemInserted(todoList.size - 1)
    }

    private fun deleteTodo(todo: Todo) {
        val position = todoList.indexOf(todo)
        if (position != -1) {
            AlertDialog.Builder(this)
                .setTitle("Delete Todo")
                .setMessage("Are you sure you want to delete this todo?")
                .setPositiveButton("Yes") { _, _ ->
                    todoList.removeAt(position)
                    todoAdapter.notifyItemRemoved(position)
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}