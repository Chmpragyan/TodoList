//
// Created by admin on 14/05/2026.
//

import Foundation
import Shared
import SwiftUI

struct AddTodoScreen: View {
    @ObservedObject var viewModel: IOSTodoViewModel
    @Environment(\.dismiss) private var dismiss

    @State private var title = ""
    @State private var description = ""

    var body: some View {
        VStack {
            Form {
                Section(header: Text("Todo Details")) {
                    TextField("Title", text: $title, axis: .vertical)
                        .padding()
                        .cornerRadius(10)
                        .textFieldStyle(.plain)
                    TextField("Description", text: $description, axis: .vertical)
                        .padding()
                        .cornerRadius(10)
                        .textFieldStyle(.plain)
                }
            }

            Button(action: {
                if let editTodo = viewModel.state.editTodo {
                    viewModel.updateTodo(id: editTodo.id, title: title, description: description)
                } else {
                    viewModel.addTodo(title: title, description: description)
                }
                dismiss()
            }) {
                Text(viewModel.state.editTodo != nil ? "Update Todo" : "Add Todo")
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(title.isEmpty ? Color.gray : Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }
            .disabled(title.isEmpty)
            .padding()
        }
        .navigationTitle(viewModel.state.editTodo != nil ? "Edit Todo" : "Add Todo")
        .onAppear {
            if let editTodo = viewModel.state.editTodo {
                title = editTodo.title
                description = editTodo.todoDescription
            }
        }
    }
}
