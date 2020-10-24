package com.fareez.dummylistingapp.di.module

import android.app.Activity
import com.fareez.dummylistingapp.presenter.MovieContract
import com.fareez.dummylistingapp.presenter.MovieDetailsContract
import com.fareez.dummylistingapp.presenter.MovieDetailsPresenter
import com.fareez.dummylistingapp.presenter.MoviePresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity{
        return activity
    }

    @Provides
    fun providePresenter(): MovieContract.Presenter{
        return MoviePresenter()
    }

    @Provides
    fun provideMovieDetailsPresenter(): MovieDetailsContract.Presenter{
        return MovieDetailsPresenter()
    }
}