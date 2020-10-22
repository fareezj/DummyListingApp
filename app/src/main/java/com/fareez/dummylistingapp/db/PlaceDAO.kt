package com.fareez.dummylistingapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fareez.dummylistingapp.model.PlacesModel

@Dao
interface PlaceDAO {

    @Query("SELECT * FROM places_table")
    fun getAllPlaces(): LiveData<List<PlacesModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(place: PlacesModel)

    @Update
    suspend fun updatePlace(place: PlacesModel)

    @Query("DELETE FROM places_table")
    suspend fun deleteAll()

}