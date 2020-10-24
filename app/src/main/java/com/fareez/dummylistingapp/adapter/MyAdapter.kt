package com.fareez.dummylistingapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.fareez.dummylistingapp.R
import com.fareez.dummylistingapp.model.MovieModel
import com.fareez.dummylistingapp.ui.MovieDetailsActivity
import com.squareup.picasso.Picasso

class MyAdapter(private val data: List<MovieModel.Details>, val context: Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        //Binding UI with PlacesModel
        fun bindItems(movieModel: MovieModel.Details){
            val tvMovieTitle = itemView.findViewById(R.id.tv_movie_title) as TextView
            val tvMovieYear = itemView.findViewById(R.id.tv_movie_year) as TextView
            val tvMovieID = itemView.findViewById(R.id.tv_movie_id) as TextView
            val ivMoviePoster = itemView.findViewById(R.id.iv_movie) as ImageView

            tvMovieTitle.text = movieModel.Title
            tvMovieYear.text = movieModel.Year.toString()
            tvMovieID.text = movieModel.imdbID
            Picasso.get()
                .load(movieModel.Poster)
                .fit()
                .error(android.R.drawable.ic_menu_report_image)
                .into(ivMoviePoster)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.get(position)?.let { holder.bindItems(it) }

        holder.itemView.setOnClickListener {

            //get position selected item
            val model = data.get(position)
            Log.i("ARYAN", model.Title)

            //get movie id (imdbID)
            val fetchedID: String = model.imdbID

            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("movieID", fetchedID)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }



}