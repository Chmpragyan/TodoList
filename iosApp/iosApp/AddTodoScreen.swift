//
// Created by admin on 14/05/2026.
//

import Foundation
import SwiftUI

struct AddTodoScreen: View {
    @Environment(\.dismiss) private var dismiss

    @State private var title = ""
    @State private var description = ""

    var body: some View {
        Form {
            TextField("Todo", text: $title)
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
        .textFieldStyle(.roundedBorder)

        Button(action: {}) {
            Text("Add Todo")
                .padding()
                .frame(maxWidth: .infinity)
                .background(Color.gray)
                .foregroundColor(.white)
                .cornerRadius(10)
        }
        .padding(.horizontal)
    }
}
