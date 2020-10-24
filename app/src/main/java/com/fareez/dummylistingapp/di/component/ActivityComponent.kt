package com.fareez.dummylistingapp.di.component

import com.fareez.dummylistingapp.di.module.ActivityModule
import com.fareez.dummylistingapp.ui.MovieActivity
import com.fareez.dummylistingapp.ui.MovieDetailsActivity
import dagger.Component

@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(movieActivity: MovieActivity)
    fun inject(movieDetailsActivity: MovieDetailsActivity)
}