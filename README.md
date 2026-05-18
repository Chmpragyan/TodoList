1. Project Overview

This project is a Kotlin Multiplatform (KMP) application that shares business logic, data persistence, and view models across Android and iOS.

- Shared Module (:shared): Contains the core logic, SQLDelight database, repositories, useCases, and the viewModel.

- Android App (:composeApp): A Jetpack Compose application that consumes the shared TodoViewModel.

- iOS App (iosApp): A SwiftUI application that also consumes the shared TodoViewModel.

2. Core Implementation (Shared Module)
A. Data Layer (SQLDelight)
Persistence is handled using SQLDelight, which generates type-safe APIs from SQL statements.

- Schema: Defined in Todo.sq, specifying the Todo table and operations (selectAllTodo, insertTodo, updateTodo, deleteTodoById).

- Database Driver: A DatabaseDriverFactory is used with expect/actual to provide platform-specific drivers (AndroidSqliteDriver and NativeSqliteDriver).

B. Domain Layer

- Model: TodoModel defines the data structure used across the app.

- Use Cases: Clean architecture is applied via use cases (GetTodoUseCase, AddTodoUseCase, etc.), ensuring the ViewModel interacts with specific business actions.

C. Presentation Layer (TodoViewModel)

The TodoViewModel is shared across platforms using androidx.lifecycle.ViewModel.

- State Management: Uses StateFlow<TodoState> to expose the current list of todos and any todo being edited.

- Concurrency: Uses viewModelScope with Dispatchers.Default to perform database operations off the main thread, ensuring smooth UI performance on both platforms.

3. Android Implementation (composeApp)

The Android UI is built with Jetpack Compose and integrated with the shared ViewModel.

Navigation

- Navigation Graph: AppNavigation uses the Navigation Component to handle transitions between three main screens:

- TodoScreen: Displays the list of tasks.

- AddTodoScreen: Used for creating or editing a task.

- TodoDetailScreen: Shows the details of a selected task.

UI Components

- Scaffold: The main App.kt uses a Scaffold with a TopAppBar that dynamically changes its title and actions (like the "Add" button) based on the current navigation route.

- State Collection: Uses collectAsStateWithLifecycle() to observe the TodoState in a lifecycle-aware manner.

4. iOS Implementation (iosApp)

The iOS app uses SwiftUI for its UI but shares the same logic.

- ViewModel Injection: The TodoViewModel is instantiated in Kotlin and accessed in Swift.

- State Observation: A wrapper (often called IOSTodoViewModel or similar) is used in Swift to observe the StateFlow from the shared module and update the SwiftUI views.

5. Implementation Summary

- Shared Persistence: SQLDelight handles the local SQLite database.

- Shared Logic: Repositories and Use Cases encapsulate the "how" and "what" of the app.

- Shared ViewModel: TodoViewModel manages the UI state and interacts with the domain layer.

- Platform UI: Android (Compose) and iOS (SwiftUI) focus purely on rendering the state and sending user events back to the shared ViewModel.

This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for the code that will be shared between all targets in the project.
  The most important subfolder is [commonMain](./shared/src/commonMain/kotlin). If preferred, you
  can add code to the platform-specific folders here too.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
