# DummyListingApp (Task 1)
Android 101 Task 1

1. Language used:
   - Kotlin
2. App criteria:
   - A listing activity using RecyclerView with the item inside using layout below within a CardView
3. Libraries used:
   - Recycle View (V1.1.0)
   - CardView (V1.0.0)
   - Picasso (V2.71828)
   
# Not So Dummy Listing App (Task 2)
Android 101 Task 2

1. Extension from DummyListingApp (Task 1)
2. Add local database
   - Room Library
3. Create new activities to add and update existing places
4. Apply MVVM Architecture
   - The app architecture refers as following guidance from codelabs:
   
   ![alt text](https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/img/1205d9f95688b35b.png)
   
   The app components are:
   
   - **MainActivity**: Displays places information using RecyclerView and PlacesAdapter. Observer in MainActivity observes the LiveData from database and update the UI if there is any changes
   - **AddPlaceActivity**: Activity to add new place
   - **PlaceViewModel**: Provides methods for accessing data layer. It returns LiveData to allow the MainActivity to setup the Observer relationship
   - **Repository**: Manages one or more data source. Acts as a single source of truth. The repository exposes methods for PlaceViewModel to interact with the database (Room)
   - **DAO**: Maps method calls to database queries. So Repository will use it to call the methods to fetch data from database (SQLite) using Room
   - **PlaceModel**: Act as a entity class

# Kinda Advance Listing App (Task 3)

~ Update from Not So Dummy ListingApp (Task 2)

- Functional Requirement:

1. Remove add item page and local database
2. Consume API from omdbAPI server
3. View results on UI 
4. Allow search movie feature

- Library Used:

1. **RxJava2**: Is known as a reactive or asynchronous programming. In reactive programming the consumer reacts to the data as it comes in.This allows to simplify the asynchronously processing of potential long running operations. It also provides a defined way of handling multiple events, errors and termination of the event stream.
The build blocks for RxJava code are the following:

   - Observables representing sources of data
   - Subscribers (or observers) listening to the observables
   - A et of methods for modifying and composing the data

2. **Retrofit**: Retrofit is a REST Client for Java and Android. It makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice. Retrofit uses the OkHttp library for HTTP requests.
To work with Retrofit we basically need the following three classes:

   - Mode class which is used as a JSON model
   - Interfaces that define the possible HTTP operations
   - Retrofit.Builder class - Instance which uses the interface and the Builder API to allow defining the URL end point for the HTTP operations.

3. **Gson**: Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object.

4. **Dagger 2**: Dagger 2 is dependency injection framework. It is based on the Java Specification Request (JSR) 330. It uses code generation and is based on annotations. The generated code is very relatively easy to read and debug.
Dagger 2 uses the following annotations:

   - @Module and @Provides: define classes and methods which provide dependencies
   - @Inject: request dependencies. Can be used on a constructor, a field, or a method
   - @Component: enable selected modules and used for performing dependency injection

- About Project:

   - The project has been refractor to use MVP pattern:
   
      ![alt text](https://miro.medium.com/max/2732/1*vMYEKJ11g-zNzRT8sfLqIQ.png)
      
   - **Model**: Use to work with data. Data source can be from local database or hosted server
   - **View**: UI implementation to display data
   - **Presenter**: Implement interactions between data(Model) and presentation(View)
   
- In between these MVP components, a base contract is defined as an interface between the View and Presenter. This would promote a cleaner code structure as the components only interact with an interface.

- Each activity has its own defined contract.

- The uniqueness of this Task2 is the usage of Dependency Injection (DI) Dagger2 which helps to maintain the components easily and provide a lossely couple architecture.

- Dagger 2 building object:

	- Dependency Providers: @Module are responsible for providing objects which can be injected. Such classes can define methods annotated with @Provides. The returned objects from these methods are available for dependency injection. This is defined at ActivityModule.kt
	- Dependencies (Consumers): @Inject annotation to receive the dependency injection. Commonly, this is defined at activities classes like MovieActivity.kt
	- Connection between Providers and Consumers: @Component which written at ActivityComponent.kt, act as an interface to define the connection beteen providers object and consumers objects. Then, Dagger2 will use this to generate code.

   
