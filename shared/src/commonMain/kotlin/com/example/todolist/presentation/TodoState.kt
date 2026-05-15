package com.example.todolist.presentation

import com.example.todolist.data.model.TodoModel

data class TodoState(
    val todos: List<TodoModel> = emptyList(),
    val editTodo: TodoModel? = null
)