package com.fareez.dummylistingapp.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class PlacesModel(
        val title: String,
        val subtitle: String,
        val description: String,
        val image: String): Parcelable
