package com.fareez.dummylistingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.fareez.dummylistingapp.Model.PlacesModel
import com.fareez.dummylistingapp.databinding.ActivityPlacesDetailsBinding
import com.squareup.picasso.Picasso

class PlacesDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_details)

        //Set viewBinding to interact with the UI
        val binding = ActivityPlacesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set actionBar back button
        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        //Fetch extra parcelable data
        val data = intent.getParcelableExtra<PlacesModel>("Place")

        //Inflate the UI with fetched data
        binding.tvTitleDetails.text = data?.title
        binding.tvSubtitleDetails.text = data?.subtitle
        binding.tvDescriptionDetails.text = data?.description
        Picasso.get()
            .load(data?.image)
            .fit()
            .error(android.R.drawable.ic_menu_report_image)
            .into(binding.ivDetails)

    }
}