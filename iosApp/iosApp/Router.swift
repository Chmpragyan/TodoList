//
// Created by admin on 15/05/2026.
//

import SwiftUI
import Shared

enum Route: Hashable {
    case todoList
    case addTodo
    case editTodo(TodoModel)
    case todoDetail(TodoModel)
}

class Router: ObservableObject {
    @Published var path = NavigationPath()
    
    func navigate(to route: Route) {
        path.append(route)
    }
    
    func pop() {
        path.removeLast()
    }
    
    func popToRoot() {
        path.removeLast(path.count)
    }
}
