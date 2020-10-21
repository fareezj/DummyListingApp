package com.fareez.dummylistingapp.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fareez.dummylistingapp.PlacesDetailsActivity
import com.fareez.dummylistingapp.Model.PlacesModel
import com.fareez.dummylistingapp.R
import com.squareup.picasso.Picasso

class MyAdapter(val arrayList: ArrayList<PlacesModel>, val context: Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //Binding UI with PlacesModel
        fun bindItems(placesModel: PlacesModel){
            val textViewTitle = itemView.findViewById(R.id.tv_title) as TextView
            val textViewSubtitle = itemView.findViewById(R.id.tv_subtitle) as TextView
            val textViewDescription = itemView.findViewById(R.id.tv_description) as TextView
            val imageViewPlace = itemView.findViewById(R.id.iv_place) as ImageView

            textViewTitle.text = placesModel.title
            textViewSubtitle.text = placesModel.subtitle
            textViewDescription.text = placesModel.description
            Picasso.get()
            .load(placesModel.image)
            .fit()
            .error(android.R.drawable.ic_menu_report_image)
            .into(imageViewPlace)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(arrayList[position])

        holder.itemView.setOnClickListener {

            //get position selected item
            val model = arrayList.get(position)
            //get details
            val gTitle: String = model.title
            val gSubtitle: String = model.subtitle
            val gDescription: String = model.description
            val gImage: String = model.image

            val intent = Intent(context, PlacesDetailsActivity::class.java)
            val parcel = PlacesModel(gTitle, gSubtitle, gDescription, gImage)

            intent.putExtra("Place", parcel)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }



}