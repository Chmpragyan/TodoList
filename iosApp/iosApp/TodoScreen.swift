//
// Created by admin on 14/05/2026.
//

import Foundation
import SwiftUI

struct TodoScreen: View {
    @StateObject var todoViewModel = IOSTodoViewModel()
    @State private var navigateToAddScreen = false

    var body: some View {
        List {
            ForEach(todoViewModel.state.todos, id: \.id) { todo in
                HStack {
                    VStack(alignment: .leading) {
                        Text(todo.title)
                            .font(.headline)
                        Text(todo.todoDescription)
                            .font(.subheadline)
                            .foregroundColor(.gray)
                    }
                    Spacer()
                    Button(action: {
                        todoViewModel.deleteTodo(id: todo.id)
                    }) {
                        Image(systemName: "trash")
                            .foregroundColor(.red)
                    }
                }

            }
            .onDelete { indexSet in
                indexSet.forEach { index in
                    let todo = todoViewModel.state.todos[index]
                    todoViewModel.deleteTodo(id: todo.id)
                }
            }
        }
        .navigationTitle("Todo")
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Button(action: {
                    navigateToAddScreen = true
                }) {
                    Image(systemName: "plus")
                }
            }
        }
        .navigationDestination(isPresented: $navigateToAddScreen) {
            AddTodoScreen(viewModel: todoViewModel)
        }
        .onAppear {
            todoViewModel.loadTodos()
        }
    }
}
