//
// Created by admin on 14/05/2026.
//

import Foundation
import SwiftUI
import Shared

struct TodoScreen: View {
    @ObservedObject var todoViewModel: IOSTodoViewModel
    @EnvironmentObject var router: Router

    var body: some View {
        List {
            ForEach(todoViewModel.state.todos, id: \.id) { todo in
                HStack {
                    VStack(alignment: .leading) {
                        Text(todo.title)
                            .font(.headline)
                            .lineLimit(1)
                        Text(todo.todoDescription)
                            .font(.subheadline)
                            .foregroundColor(.gray)
                            .lineLimit(2)
                    }
                    .contentShape(Rectangle())
                    .onTapGesture {
                        router.navigate(to: .todoDetail(todo))
                    }
                    
                    Spacer()

                    // Edit Button
                    Button(action: {
                        todoViewModel.selectTodo(todo: todo)
                        router.navigate(to: .editTodo(todo))
                    }) {
                        Image(systemName: "pencil")
                            .foregroundColor(.black)
                            .frame(width: 44, height: 44)
                            .background(Color.white)
                            .clipShape(Circle())
                            .overlay(
                                Circle()
                                    .stroke(Color.gray.opacity(0.2), lineWidth: 0.5)
                            )
                    }
                    .buttonStyle(.plain)

                    // Delete Button
                    Button(action: {
                        todoViewModel.deleteTodo(id: todo.id)
                    }) {
                        Image(systemName: "trash")
                            .foregroundColor(.red)
                            .frame(width: 44, height: 44)
                            .background(.white)
                            .clipShape(Circle())
                            .overlay(
                                Circle()
                                    .stroke(Color.gray.opacity(0.2), lineWidth: 0.5))

                    }
                    .buttonStyle(.plain)
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
                    todoViewModel.selectTodo(todo: nil)
                    router.navigate(to: .addTodo)
                }) {
                    Image(systemName: "plus")
                }
            }
        }
        .onAppear {
            todoViewModel.loadTodos()
        }
    }
}
