package com.fareez.dummylistingapp.network.api

import com.fareez.dummylistingapp.model.MovieDetailsModel
import com.fareez.dummylistingapp.model.MovieModel
import com.fareez.dummylistingapp.util.Constants
import com.fareez.dummylistingapp.util.DateTypeDeserializer
import com.google.gson.GsonBuilder
import io.reactivex.Observable
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

//    public void onResponse(Call<UserList> call, Response<UserList> response) {
//
//        UserList userList = response.body();
//        Integer text = userList.page;
//        Integer total = userList.total;
//        Integer totalPages = userList.totalPages;
//        List<UserList.Datum> datumList = userList.data;
//        Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();
//
//        for (UserList.Datum datum : datumList) {
//            Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
//        }

}

