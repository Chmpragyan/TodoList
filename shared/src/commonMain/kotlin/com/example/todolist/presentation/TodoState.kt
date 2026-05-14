package com.example.todolist.presentation

import com.example.todolist.data.model.Todo

data class TodoState(
    val todos: List<Todo> = emptyList(),
    val editTodo: Todo? = null
)