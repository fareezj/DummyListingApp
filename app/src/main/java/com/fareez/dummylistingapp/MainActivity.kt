package com.fareez.dummylistingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.fareez.dummylistingapp.ui.MovieActivity
import com.fareez.dummylistingapp.ui.MovieListActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MovieListActivity::class.java)
        startActivity(intent)

//        //Create placesList object
//        var placesList: ArrayList<PlacesModel>? = null
//        placesList = Constants.defaultplacesList()
//
//        //Initialize adapter to recyclerView
//        val myAdapter = MyAdapter(placesList, this)
//        val recyclerView = binding.rvMain
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = myAdapter

    }
}