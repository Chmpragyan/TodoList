//
// Created by admin on 15/05/2026.
//

import SwiftUI
import Shared

struct AppNavigation: View {
    @StateObject private var router = Router()
    @StateObject private var todoViewModel = IOSTodoViewModel()

    var body: some View {
        NavigationStack(path: $router.path) {
            TodoScreen(todoViewModel: todoViewModel)
                .navigationDestination(for: Route.self) { route in
                    switch route {
                    case .todoList:
                        TodoScreen(todoViewModel: todoViewModel)
                    case .addTodo:
                        AddTodoScreen(viewModel: todoViewModel)
                    case .editTodo(_):
                        AddTodoScreen(viewModel: todoViewModel)
                    case .todoDetail(let todo):
                        TodoItemScreen(todo: todo)
                    }
                }
        }
        .environmentObject(router)
    }
}
