import Shared
import SwiftUI

struct ContentView: View {
    @State private var showContent = false
    var body: some View {
        NavigationStack {
            TodoScreen()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}