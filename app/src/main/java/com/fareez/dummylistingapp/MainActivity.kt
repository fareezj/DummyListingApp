package com.fareez.dummylistingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fareez.dummylistingapp.adapter.PlacesAdapter
import com.fareez.dummylistingapp.model.PlacesModel
import com.fareez.dummylistingapp.viewModel.PlaceViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val newPlaceActivityRequestCode = 1
    private lateinit var placeViewModel: PlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize RecyclerView
        val recyclerView = rv_main
        val adapter = PlacesAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize PlaceViewModel
        placeViewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)

        // Observe changes from database using LiveData
        placeViewModel.allPlaces.observe(this, Observer { places ->
            // Update the cached copy of the words in the adapter.
            places?.let { adapter.setPlace(it) }
        })

        // FAB to add new place activity
        fab_addPlace.setOnClickListener {
            val intent = Intent(this@MainActivity, AddPlaceActivity::class.java)
            startActivityForResult(intent, newPlaceActivityRequestCode)
        }
    }
    // Fetch results from other activity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Safety check
        if (requestCode == newPlaceActivityRequestCode && resultCode == Activity.RESULT_OK) {
            //Get parcelable data
            data?.getParcelableExtra<PlacesModel>(AddPlaceActivity.EXTRA_REPLY)?.let {
                val word = it
                //Insert to database through PlaceViewModel
                placeViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Not Save",
                Toast.LENGTH_LONG).show()
        }
    }
}