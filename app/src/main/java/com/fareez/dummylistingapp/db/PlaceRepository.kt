package com.fareez.dummylistingapp.db

import androidx.lifecycle.LiveData
import com.fareez.dummylistingapp.model.PlacesModel

class PlaceRepository(private val placeDAO: PlaceDAO) {

    val allPlaces: LiveData<List<PlacesModel>> = placeDAO.getAllPlaces()

    suspend fun insert(place: PlacesModel){
        placeDAO.insert(place)
    }

    suspend fun updatePlace(place: PlacesModel){
        placeDAO.updatePlace(place)
    }

    suspend fun deleteAll(){
        placeDAO.deleteAll()
    }

}