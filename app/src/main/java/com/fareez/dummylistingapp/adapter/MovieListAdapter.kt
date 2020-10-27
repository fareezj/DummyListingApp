package com.fareez.dummylistingapp.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fareez.dummylistingapp.model.Details
import com.fareez.dummylistingapp.network.State
import com.fareez.dummylistingapp.ui.MovieDetailsActivity
import com.fareez.dummylistingapp.ui.MovieListActivity

class MovieListAdapter(private val retry: () -> Unit, val context: Context)
    : PagedListAdapter<Details, RecyclerView.ViewHolder>(MovieDiffCallback){

    private val DATA_VIEW_TYPE = 1
    private val FOOTER_VIEW_TYPE = 2
    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) {
            MovieViewHolder.create(parent)
        }
        else {
            ListFooterViewHolder.create(retry, parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE){

            (holder as MovieViewHolder).bind(getItem(position))
            holder.itemView.setOnClickListener {

                //get position selected item
                val model = getItem(position)

                //get movie id (imdbID)
                val fetchedID: String = model?.imdbID ?: ""

                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("movieID", fetchedID)
                context.startActivity(intent)
            }
        }
        else {
            (holder as ListFooterViewHolder).bind(state)
        }
    }

    companion object{
        val MovieDiffCallback = object: DiffUtil.ItemCallback<Details>(){
            override fun areItemsTheSame(oldItem: Details, newItem: Details): Boolean {
                return oldItem.imdbID == newItem.imdbID
            }

            override fun areContentsTheSame(oldItem: Details, newItem: Details): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int{
        return super.getItemCount() + if(hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }
}