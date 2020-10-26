package com.fareez.dummylistingapp.model

import com.google.gson.annotations.SerializedName

class MovieModel {

        @SerializedName("Search")
        var search: List<Details>? = null

        class Details {
            var Title: String = ""
            var Year: String = ""
            var Rated: String = ""
            var Released: String = ""
            var Director: String = ""
            var Actors: String = ""
            var Plot: String = ""
            var Poster: String = ""
            var Language: String = ""
            var imdbRating: Double = 0.0
            var Production: String = ""
            var imdbID: String = ""
        }


}



//var data: List<Datum>? = null
//
//class Datum {
//    @SerializedName("id")
//    var id: Int? = null
//
//    @SerializedName("name")
//    var name: String? = null
//
//    @SerializedName("year")
//    var year: Int? = null
//
//    @SerializedName("pantone_value")
//    var pantoneValue: String? = null
//}