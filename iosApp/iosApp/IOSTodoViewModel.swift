//
// Created by admin on 15/05/2026.
//

import Foundation
import SwiftUI
import Shared

@MainActor
class IOSTodoViewModel: ObservableObject {
    @Published var state = TodoState(todos: [], editTodo: nil)
    private let viewModel: TodoViewModel

    init(viewModel: TodoViewModel) {
        self.viewModel = viewModel
        viewModel.observeState { state in
            self.state = state
        }
    }

    convenience init() {
        self.init(viewModel: TodoViewModel(driverFactory: IosDatabaseDriverFactory()))
    }

    func loadTodos() {
        viewModel.loadTodos()
    }

    func addTodo(title: String, description: String) {
        viewModel.addTodo(title: title, description: description)
    }

    func updateTodo(id: Int64, title: String, description: String) {
        viewModel.updateTodo(id: id, title: title, description: description)
    }

    func selectTodo(todo: TodoModel) {
        viewModel.selectTodo(todo: todo)
    }

    func deleteTodo(id: Int64) {
        viewModel.deleteTodo(id: id)
    }

    // deinit {
    //     viewModel.clear()
    // }
}
