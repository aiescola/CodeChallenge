package com.aitor.domestikacodechallenge

import android.app.Application
import com.aitor.domestikacodechallenge.di.apiModule
import com.aitor.domestikacodechallenge.di.coursesModule
import com.aitor.domestikacodechallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DomestikaCodeChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = listOf(
            apiModule,
            coursesModule,
            viewModelModule
        )
        startKoin {
            androidContext(this@DomestikaCodeChallengeApplication)
            modules(appModules)
        }
    }
}
