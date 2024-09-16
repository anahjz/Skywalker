# Skywalker
Skywalker
Skywalker is a sample Android app designed to showcase the architecture and best practices for building scalable and maintainable applications. This project demonstrates the use of modern Android development tools, libraries, and patterns to create a simple character search and display app. The app uses the Star Wars API (SWAPI) to fetch character information and display it in a user-friendly manner.

Features
Search and display Star Wars characters.
View detailed information about each character.
Handle loading states with a progress indicator.
Efficient navigation using the Jetpack Navigation component.
Responsive UI following Material Design principles.
Architecture
Skywalker follows the MVVM (Model-View-ViewModel) architecture pattern, which helps separate concerns and improve testability and maintainability.

MVVM Components:
Model: The data layer, responsible for managing the app's data, using the repository pattern to abstract data sources (e.g., API, database).
ViewModel: Acts as a bridge between the View and the Model. It holds the UI-related data and handles business logic while being lifecycle-aware.
View: The UI layer, responsible for rendering the data provided by the ViewModel and observing any changes.

Benefits of MVVM:
1. Lifecycle awareness for handling configuration changes.
2. Better testability of the business logic.

Libraries Used
1. Jetpack Components
   Navigation: Manages in-app navigation and ensures the UI back stack is handled properly. It simplifies navigation between different screens in a single-activity architecture.
   Why used? It handles navigation efficiently and is lifecycle-aware, reducing boilerplate code.
   ViewModel: Manages UI-related data in a lifecycle-conscious way, allowing data to survive configuration changes like screen rotations.
   Why used? ViewModel separates business logic from UI and ensures the UI does not hold references to data sources directly.
   LiveData: An observable data holder class, used for data that needs to be observed, such as UI updates.
   Why used? LiveData is lifecycle-aware and only updates observers that are in an active state, preventing memory leaks.
2. Retrofit
   A type-safe HTTP client for Android used for making network requests to the Star Wars API (SWAPI).

3. Hilt
   A dependency injection library for Android that reduces boilerplate code and simplifies dependency management in the app.
   Why used? Hilt simplifies providing dependencies, such as ViewModels, and ensures proper lifecycle management.

4. Gson
   A JSON parsing library used in conjunction with Retrofit to convert JSON data from the API into Kotlin objects.
   Why used? Gson is fast, efficient, and works seamlessly with Retrofit to handle JSON serialization and deserialization
