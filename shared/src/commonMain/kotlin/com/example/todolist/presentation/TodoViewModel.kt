package com.example.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.DatabaseDriverFactory
import com.example.todolist.data.model.Todo
import com.example.todolist.data.repository.TodoRepository
import com.example.todolist.database.Database
import com.example.todolist.domain.usecase.AddTodoUseCase
import com.example.todolist.domain.usecase.DeleteTodoUseCase
import com.example.todolist.domain.usecase.GetTodoUseCase
import com.example.todolist.domain.usecase.UpdateTodoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModel(driverFactory: DatabaseDriverFactory) : ViewModel() {
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
        viewModelScope.launch {
            // Dispatchers.Default runs on background threads across Android and iOS
            val updatedList = withContext(Dispatchers.Default) {
                getTodosUseCase()
            }
            _state.update { current ->
                current.copy(todos = updatedList)
            }
        }
    }

    fun addTodo(title: String, description: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                addTodoUseCase(title, description)
            }
            loadTodos()
        }
    }

    fun updateTodo(id: Long, title: String, description: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                updateTodoUseCase(id, title, description)
            }
            loadTodos()
            _state.update { current ->
                current.copy(editTodo = null)
            }
        }
    }

    fun selectTodo(todo: Todo?) {
        _state.update { current ->
            current.copy(editTodo = todo)
        }
    }

    fun deleteTodo(id: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                deleteTodoUseCase(id)
            }
            loadTodos()
        }
    }
}