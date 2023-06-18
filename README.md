#Android App Documentation


<h2>IDE & Device</h2>
1. IDE Uses :- Android Studio Hedgehog | 2023.1.1 Canary 8
2. Android Device For Testing:- Android 13, Android 12 and Android 10

<h2>Technologies Used</h2>

1. Jetpack Compose
2. Clean Architecture with Repository Design Pattern
3. Retrofit and OkHttp
4. Room
5. Navigation
6. Dagger Hilt
7. Coroutines
8. Kotlin Gradle (.kts)
9. Version Catalog (.toml)
10. Vonage (SMS Service)


<h2>App Architecture</h2>

1. AppController (For define controller class and initialize HILT.)
2. UseCases (Use for call api or fetching data from DB.)
3. ViewModels (For fetch data from use-cases and handle states.)
4. Views (Handle UI in compose).
5. Presentation Layer handle UI States.
6. Data Layer handle data classes (dto:- Data Transfer Object) or repository implementations.
7. Domain Layer responsible for repository interfaces and use cases.
8. DI :- Initialize all modules in di package and provide singleton instance or database or retrofit.
9. DB :- Initialize Dao and DataBase abstract classes
10. Vonage use with retrofit for sending SMS.


<h2>Benefits</h2>

1. Clean Architecture is now mostly use for create app instead of MVVM. Because its separate our network and business logic.
2. Room:- For store data data like Contacts or Messages.
3. We are read json file and store all contacts in DB. Read json data in background thread.
4. Retrofit:- For calling API with OkHttp and Repository Design Pattern.
5. I am using Jetpack Compose for create UI. It's latest technology for create Declarative UI.


<h2>Thank You.</h2>