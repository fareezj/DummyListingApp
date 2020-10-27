package com.fareez.dummylistingapp.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fareez.dummylistingapp.R
import com.fareez.dummylistingapp.model.Details
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*


class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(movie: Details?){
        if(movie != null){
            itemView.tv_movie_title.text = movie.Title
            if(!movie.Poster.isNullOrEmpty()){
                Picasso.get().load(movie.Poster).into(itemView.img_news_banner)
            }
        }

    }

    companion object{
        fun create(parent: ViewGroup): MovieViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
            return MovieViewHolder(view)
        }
    }


}