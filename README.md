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
   
