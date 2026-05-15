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
                    TextField("Title", text: $title)
                        .padding()
                        .cornerRadius(10)
                        .textFieldStyle(.plain)
                        .overlay(
                            RoundedRectangle(cornerRadius: 10)
                                .stroke(Color.gray, lineWidth: 1)
                        )
                    TextField("Description", text: $description)
                        .padding()
                        .cornerRadius(10)
                        .textFieldStyle(.plain)
                        .overlay(
                            RoundedRectangle(cornerRadius: 10)
                                .stroke(Color.gray, lineWidth: 1)
                        )
                }
            }

            Button(action: {
                viewModel.addTodo(title: title, description: description)
                dismiss()
            }) {
                Text("Add Todo")
                    .padding()
                    .frame(maxWidth: .infinity)
                    .background(title.isEmpty ? Color.gray : Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }
            .disabled(title.isEmpty)
            .padding()
        }
        .navigationTitle("Add Todo")
    }
}
