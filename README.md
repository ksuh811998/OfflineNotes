# Offline Notes - Offline-First Task Manager

Offline Notes is a professional Android application built to demonstrate modern Android development practices, including Clean Architecture, MVVM, and an offline-first data strategy.

## 🚀 Features
- **Offline-First**: View, add, and manage tasks without an internet connection. Data is persisted locally using Room.
- **Background Sync**: Uses WorkManager to automatically synchronize tasks with a remote API when the network is available.
- **Real-time Connectivity Monitoring**: Displays a "Connected to Cloud / Offline Mode" status indicator using a Flow-based Connectivity Observer.
- **Modern UI**: Built entirely with Jetpack Compose and Material 3 design components, featuring swipe-to-dismiss actions.
- **Task Management**: Create tasks with titles, descriptions, and priority levels (High, Medium, Low).

## 🛠 Tech Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose (100%)
- **Architecture**: MVVM + Clean Architecture (Data, Domain, Presentation layers)
- **Dependency Injection**: Hilt
- **Local Database**: Room
- **Networking**: Retrofit + OkHttp
- **Background Tasks**: WorkManager
- **Asynchronous Flow**: Kotlin Coroutines + StateFlow / SharedFlow

## 📡 APIs Used
- **JSONPlaceholder API**: Used to simulate fetching remote tasks from a server.
- **Task Mapping**: Includes a custom mapper that transforms remote placeholder data into readable English task names.
- **Connectivity Manager API**: System service utilized for real-time network monitoring.

## 🏗 Project Structure
- `data`: Implementation of Repository, Room Database, Retrofit API, and Workers.
- `domain`: Core business logic including Domain Models, Repository Interfaces, and Use Cases.
- `presentation`: UI layer containing ViewModels and Compose Screens.
- `di`: Hilt Modules for dependency injection.
- `util`: Utility classes like the Connectivity Observer.

## 📸 Screenshots
*(Add your screenshots here to showcase the "Connected to Cloud" and "Offline Mode" states)*

---
Developed by [ksuh811998 - Suhrit Karn](https://github.com/ksuh811998) | [LinkedIn](https://www.linkedin.com/in/suhritkarn)
