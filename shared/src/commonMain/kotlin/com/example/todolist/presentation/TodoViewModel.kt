package com.example.todolist.presentation

import com.example.todolist.DatabaseDriverFactory
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.database.Database
import com.example.todolist.domain.usecase.AddTodoUseCase
import com.example.todolist.domain.usecase.DeleteTodoUseCase
import com.example.todolist.domain.usecase.GetTodoUseCase
import com.example.todolist.domain.usecase.UpdateTodoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TodoViewModel(driverFactory: DatabaseDriverFactory) {
    private val repository = TodoRepository(
        Database(driverFactory)
    )

    private val addTodoUseCase = AddTodoUseCase(repository)
    private val getTodosUseCase = GetTodoUseCase(repository)
    private val updateTodoUseCase = UpdateTodoUseCase(repository)
    private val deleteTodoUseCase = DeleteTodoUseCase(repository)

    private val _state = MutableStateFlow(TodoState())
    val state: StateFlow<TodoState> = _state.asStateFlow()

    init {
        loadTodos()
    }

    fun loadTodos() {
        _state.value = _state.value.copy(
            todos = getTodosUseCase()
        )
    }

    fun addTodo(title: String, description: String) {
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