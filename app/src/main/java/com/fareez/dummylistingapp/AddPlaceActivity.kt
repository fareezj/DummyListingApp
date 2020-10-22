package com.fareez.dummylistingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.fareez.dummylistingapp.model.PlacesModel
import com.fareez.dummylistingapp.viewModel.PlaceViewModel
import kotlinx.android.synthetic.main.activity_add_place.*

class AddPlaceActivity : AppCompatActivity() {

    private lateinit var placeViewModel: PlaceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_place)

        //Set actionBar back button
        val actionBar : ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        //Button to save new place
        btn_save.setOnClickListener {

            // Intent to be received by MainActivity
            val replyIntent = Intent()
            if (TextUtils.isEmpty(et_title.text) || TextUtils.isEmpty(et_subtitle.text) ||
                TextUtils.isEmpty(et_desc.text) || TextUtils.isEmpty(et_image.text)) {

                setResult(Activity.RESULT_CANCELED, replyIntent)

            } else {

                val title = et_title.text.toString()
                val subtitle = et_subtitle.text.toString()
                val description = et_desc.text.toString()
                val image = et_image.text.toString()

                val data = PlacesModel(0, title, subtitle, description, image)
                replyIntent.putExtra(EXTRA_REPLY, data)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        //Button to delete all data
        btn_deleteAll.setOnClickListener {
            placeViewModel = ViewModelProvider(this).get(PlaceViewModel::class.java)
            placeViewModel.deleteAll()
        }
    }

    //Static
    companion object {
        const val EXTRA_REPLY = "REPLY"
    }
}