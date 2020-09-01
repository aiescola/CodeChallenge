package com.aitor.domestikacodechallenge.di

import com.aitor.domestikacodechallenge.BuildConfig
import com.aitor.domestikacodechallenge.R
import com.aitor.domestikacodechallenge.data.network.createApi
import com.aitor.domestikacodechallenge.data.network.createRetrofit
import com.aitor.domestikacodechallenge.data.network.datasource.CourseNetworkDataSource
import com.aitor.domestikacodechallenge.data.network.service.CourseService
import com.aitor.domestikacodechallenge.data.repository.CourseRepositoryImpl
import com.aitor.domestikacodechallenge.domain.repository.CourseRepository
import com.aitor.domestikacodechallenge.domain.usecases.FetchCoursesUseCase
import com.aitor.domestikacodechallenge.screens.courselist.CourseListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single {
        createRetrofit(androidContext().getString(R.string.base_url), BuildConfig.DEBUG)
    }
    single<CourseService> {
        createApi(get())
    }
}

val coursesModule = module {
    single { CourseNetworkDataSource(get()) }
    single<CourseRepository> { CourseRepositoryImpl(get()) }
    factory { FetchCoursesUseCase(get()) }
}

val viewModelModule = module {
    viewModel {
        CourseListViewModel(
            get()
        )
    }
}
