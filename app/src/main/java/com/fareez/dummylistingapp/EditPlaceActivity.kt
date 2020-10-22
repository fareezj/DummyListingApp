package com.fareez.dummylistingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.fareez.dummylistingapp.model.PlacesModel
import com.fareez.dummylistingapp.databinding.ActivityEditPlaceBinding
import com.fareez.dummylistingapp.viewModel.PlaceViewModel
import kotlinx.android.synthetic.main.activity_edit_place.*

class EditPlaceActivity : AppCompatActivity() {

    private lateinit var placeViewModel: PlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_place)

        //Set actionBar back button
        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        //Fetch extra parcelable data
        val fetchedEditData = intent.getParcelableExtra<PlacesModel>("EditPlace")

        //Inflate the UI with fetched data
        et_title_edit.setText(fetchedEditData?.title)
        et_subtitle_edit.setText(fetchedEditData?.subtitle)
        et_desc_edit.setText(fetchedEditData?.description)
        et_image_edit.setText(fetchedEditData?.image)

        //Update new place data
        val updatedTitle = et_title_edit.text
        val updatedSubtitle = et_subtitle_edit.text
        val updatedDesc= et_desc_edit.text
        val updatedImage = et_image_edit.text

        btn_edit.setOnClickListener {
            //Initialize lateinit
            placeViewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)
            val updatedData = PlacesModel(
                fetchedEditData!!.id,
                updatedTitle.toString(),
                updatedSubtitle.toString(),
                updatedDesc.toString(),
                updatedImage.toString()
            )
            placeViewModel.updatePlace(updatedData)
            finish()
        }
    }
}