package com.fareez.dummylistingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fareez.dummylistingapp.Adapter.MyAdapter
import com.fareez.dummylistingapp.Data.Constants
import com.fareez.dummylistingapp.Model.PlacesModel
import com.fareez.dummylistingapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set viewBinding to interact with the UI
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Create placesList object
        var placesList: ArrayList<PlacesModel>? = null
        placesList = Constants.defaultplacesList()

        //Initialize adapter to recyclerView
        val myAdapter = MyAdapter(placesList, this)
        val recyclerView = binding.rvMain
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter

    }
}