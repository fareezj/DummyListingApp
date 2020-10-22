package com.fareez.dummylistingapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "places_table")
data class PlacesModel(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @ColumnInfo(name = "place_title")
        val title: String,
        @ColumnInfo(name = "place_subtitle")
        val subtitle: String,
        @ColumnInfo(name = "place_description")
        val description: String,
        @ColumnInfo(name = "place_image")
        val image: String
): Parcelable
