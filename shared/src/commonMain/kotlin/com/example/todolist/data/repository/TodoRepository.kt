package com.example.todolist.data.repository

import com.example.todolist.data.model.Todo
import com.example.todolist.database.Database

class TodoRepository(database: Database) {
    private val queries = database.todoQueries

    fun getTodos(): List<Todo> {
        return queries
            .selectAllTodo()
            .executeAsList()
            .map {
                Todo(
                    title = it.title,
                    description = it.description
                )
            }
    }

    fun addTodo(title: String, description: String) {
        queries.insertTodo(
            title = title,
            description = description,
        )
    }

    fun updateTodo(
        id: Long,
        title: String,
        description: String
    ) {
        queries.updateTodo(
            id = id,
            title = title,
            description = description
        )
    }

    fun deleteTodo(id: Long) {
        queries.deleteTodoById(id)
    }
}