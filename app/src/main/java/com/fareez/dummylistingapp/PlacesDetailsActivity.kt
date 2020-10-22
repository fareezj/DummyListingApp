package com.fareez.dummylistingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.fareez.dummylistingapp.model.PlacesModel
import com.fareez.dummylistingapp.databinding.ActivityPlacesDetailsBinding
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_places_details.*

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
        val fetchedData = intent.getParcelableExtra<PlacesModel>("Place")

        //Inflate the UI with fetched data
        tv_title_details.text = fetchedData?.title
        tv_subtitle_details.text = fetchedData?.subtitle
        tv_description_details.text = fetchedData?.description
        Picasso.get()
            .load(fetchedData?.image)
            .fit()
            .error(android.R.drawable.ic_menu_report_image)
            .into(iv_details)

        //FAB to edit activity
        fab_update.setOnClickListener {
            if(fetchedData != null){
                val placeId: Int = fetchedData.id
                val placeTitle: String = fetchedData.title
                val placeSubtitle: String = fetchedData.subtitle
                val placeDescription: String = fetchedData.description
                val placeImage: String = fetchedData.image

                val intent = Intent(this, EditPlaceActivity::class.java)
                val parcel = PlacesModel(placeId, placeTitle, placeSubtitle,
                    placeDescription, placeImage)

                intent.putExtra("EditPlace", parcel)
                startActivity(intent)
            }
        }
    }
}