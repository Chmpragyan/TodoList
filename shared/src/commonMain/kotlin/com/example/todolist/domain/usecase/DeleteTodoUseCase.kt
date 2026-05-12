package com.example.todolist.domain.usecase

import com.example.todolist.data.repository.TodoRepository

class DeleteTodoUseCase(
    private val repository: TodoRepository
) {
    operator fun invoke(id: Long) {
        repository.deleteTodo(id)
    }
}