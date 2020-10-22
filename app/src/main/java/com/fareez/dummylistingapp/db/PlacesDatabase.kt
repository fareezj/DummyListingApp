package com.fareez.dummylistingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fareez.dummylistingapp.model.PlacesModel
import kotlinx.coroutines.CoroutineScope


@Database(entities = [PlacesModel::class], version = 1, exportSchema = false)
public abstract class PlacesDatabase: RoomDatabase() {

    abstract fun placeDao(): PlaceDAO

        companion object {
            // Singleton prevents multiple instances of database opening at the
            // same time.
            @Volatile
            private var INSTANCE: PlacesDatabase? = null

            fun getDatabase(context: Context): PlacesDatabase {
                val tempInstance = INSTANCE
                if (tempInstance != null) {
                    return tempInstance
                }
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlacesDatabase::class.java,
                        "place_database"
                    )
                        .build()
                    INSTANCE = instance
                    return instance
                }
            }
        }



    //    private class PlacesDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            INSTANCE?.let { database ->
//                scope.launch {
//                    var placesDao = database.placeDao()
//
//                    // Delete all content here.
//                    placesDao.deleteAll()
//
////                    var place = PlacesModel(1, "KL", "KL", "KL", "KL")
////                    placesDao.insert(place)
//
//                }
//            }
//        }
//    }
}