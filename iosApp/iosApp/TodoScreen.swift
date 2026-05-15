//
// Created by admin on 14/05/2026.
//

import Foundation
import SwiftUI

struct TodoScreen: View {
    @State private var navigateToAddScreen = false

    var body: some View {
        List {
            Text("Sample Todo Item 1")
            Text("Sample Todo Item 2")
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
            AddTodoScreen()
        }
    }
}