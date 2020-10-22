package com.fareez.dummylistingapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fareez.dummylistingapp.model.PlacesModel
import com.fareez.dummylistingapp.PlacesDetailsActivity
import com.fareez.dummylistingapp.R
import com.squareup.picasso.Picasso

class PlacesAdapter (val context: Context): RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>(){

    private var places = emptyList<PlacesModel>()

    class PlacesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindItems(placesModel: PlacesModel){
            val tvPlaceTitle: TextView = itemView.findViewById(R.id.tv_title)
            val tvPlaceSubtitle: TextView = itemView.findViewById(R.id.tv_subtitle)
            val tvPlaceDescription: TextView = itemView.findViewById(R.id.tv_description)
            val ivPlaceImage: ImageView = itemView.findViewById(R.id.iv_place)

            tvPlaceTitle.text = placesModel.title
            tvPlaceSubtitle.text = placesModel.subtitle
            tvPlaceDescription.text = placesModel.description
            Picasso.get()
                .load(placesModel.image)
                .fit()
                .error(android.R.drawable.ic_menu_report_image)
                .into(ivPlaceImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row, parent, false)

        return PlacesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {

        holder.bindItems(places[position])

        holder.itemView.setOnClickListener {

            val current = places.get(position)
            val getId: Int = current.id
            val getTitle: String = current.title
            val getSubtitle: String = current.subtitle
            val getDescription: String = current.description
            val getImage: String = current.image

            val intent = Intent(context, PlacesDetailsActivity::class.java)
            val parcel = PlacesModel(getId, getTitle, getSubtitle, getDescription, getImage)

            intent.putExtra("Place", parcel)
            context.startActivity(intent)
        }

    }

    internal fun setPlace(place: List<PlacesModel>) {
        this.places = place
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return places.size
    }
}