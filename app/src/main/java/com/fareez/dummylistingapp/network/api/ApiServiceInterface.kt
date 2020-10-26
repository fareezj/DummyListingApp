package com.fareez.dummylistingapp.network.api

import com.fareez.dummylistingapp.model.MovieDetailsModel
import com.fareez.dummylistingapp.model.MovieModel
import com.fareez.dummylistingapp.util.Constants
import com.fareez.dummylistingapp.util.DateTypeDeserializer
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.sql.Time
import java.util.*
import java.util.concurrent.TimeUnit

interface ApiServiceInterface {

    @GET("/")
    fun getMovies(
        @Query("s") title: String,
        @Query("apikey") apiKey: String) : Observable <MovieModel>

    @GET("/")
    fun getMovieDetails(
        @Query("i") title: String,
        @Query("apikey") apiKey: String) : Observable <MovieDetailsModel>

    @GET("/")
    fun getMoviesByPage(
        @Query("s") title: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String) : Single<MovieModel>

    companion object Factory {
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .registerTypeAdapter(Date::class.java, DateTypeDeserializer())
                            .setLenient().create()))
                .baseUrl(Constants.BASE_URL)
                .client(
                    OkHttpClient.Builder()
                        .readTimeout(120, TimeUnit.SECONDS)
                        .connectTimeout(120, TimeUnit.SECONDS)
                        .build())
                .build()
        }
        fun create(): ApiServiceInterface {
            return retrofit.create(ApiServiceInterface::class.java)
        }
    }

}

