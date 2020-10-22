package com.fareez.dummylistingapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fareez.dummylistingapp.db.PlaceRepository
import com.fareez.dummylistingapp.db.PlacesDatabase
import com.fareez.dummylistingapp.model.PlacesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlaceViewModel(application: Application): AndroidViewModel(application) {

    private val repository: PlaceRepository

    val allPlaces: LiveData<List<PlacesModel>>

    init {
        val placeDAO = PlacesDatabase.getDatabase(application).placeDao()
        repository = PlaceRepository(placeDAO)
        allPlaces = repository.allPlaces
    }

    fun insert(place: PlacesModel) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(place)
    }

    fun updatePlace(place: PlacesModel) = viewModelScope.launch(Dispatchers.IO){
        repository.updatePlace(place)
    }

    fun deleteAll() = viewModelScope.launch (Dispatchers.IO){
        repository.deleteAll()
    }

}