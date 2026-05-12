package com.example.todolist.data.repository

import com.example.todolist.Todo
import com.example.todolist.database.Database

class TodoRepository(database: Database) {

    private val queries = database.todoQueries

    fun getAllTodos(): List<Todo> = queries.selectAllTodo().executeAsList()

    fun addTodo(title: String, description: String) {
        queries.insertTodo(title, description)
    }

    fun updateTodo(id: Long, title: String, description: String) {
        queries.updateTodo(title, description, id)
    }

    fun deleteTodo(id: Long) {
        queries.deleteTodoById(id)
    }
}