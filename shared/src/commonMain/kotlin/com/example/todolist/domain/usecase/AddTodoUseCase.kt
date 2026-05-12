package com.example.todolist.domain.usecase

import com.example.todolist.data.repository.TodoRepository

class AddTodoUseCase(private val todoRepository: TodoRepository) {
    operator fun invoke(title: String, description: String) {
        todoRepository.addTodo(title, description)
    }
}