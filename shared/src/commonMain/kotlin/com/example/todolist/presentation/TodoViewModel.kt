package com.example.todolist.presentation

import com.example.todolist.DatabaseDriverFactory
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.database.Database
import com.example.todolist.domain.usecase.AddTodoUseCase
import com.example.todolist.domain.usecase.DeleteTodoUseCase
import com.example.todolist.domain.usecase.GetTodoUseCase
import com.example.todolist.domain.usecase.UpdateTodoUseCase

class TodoViewModel(driverFactory: DatabaseDriverFactory) {
    private val repository = TodoRepository(
        Database(driverFactory)
    )

    private val addTodoUseCase = AddTodoUseCase(repository)
    private val getTodosUseCase = GetTodoUseCase(repository)
    private val updateTodoUseCase = UpdateTodoUseCase(repository)
    private val deleteTodoUseCase = DeleteTodoUseCase(repository)

    private var _state = TodoState()
    val state: TodoState
        get() = _state

    init {
        loadTodos()
    }

    private fun loadTodos() {
        _state = _state.copy(
            todos = getTodosUseCase()
        )
    }

    private fun addTodo(title: String, description: String) {
        addTodoUseCase(title, description)
        loadTodos()
    }

    private fun updateTodo(id: Long, title: String, description: String) {
        updateTodoUseCase(id, title, description)
    }

    private fun deleteTodo(id: Long) {
        deleteTodoUseCase(id)
        loadTodos()
    }
}