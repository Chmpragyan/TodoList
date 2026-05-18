//
// Created by admin on 15/05/2026.
//

import Foundation
import SwiftUI
import Shared

struct TodoItemScreen: View {
    let todo: TodoModel

    var body: some View {
        VStack(alignment: .leading, spacing: 20) {

            Text(todo.title)
                .font(.largeTitle)
                .fontWeight(.semibold)

            Divider()

            Text(todo.todoDescription)
                .font(.body)
                .foregroundColor(.secondary)
                .lineSpacing(5)

            Spacer()
        }
        .padding(24)
        .navigationTitle("Note")
        .navigationBarTitleDisplayMode(.inline)
    }
}
