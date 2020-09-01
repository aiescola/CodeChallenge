package com.aitor.domestikacodechallenge.data

import com.aitor.domestikacodechallenge.data.network.createApi
import com.aitor.domestikacodechallenge.data.network.createRetrofit
import com.aitor.domestikacodechallenge.data.network.datasource.CourseNetworkDataSource
import com.aitor.domestikacodechallenge.data.network.service.CourseService
import com.aitor.domestikacodechallenge.data.repository.CourseRepositoryImpl
import com.aitor.domestikacodechallenge.domain.common.fold
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val retrofit = createRetrofit("http://mobile-assets.domestika.org", true)
    val networkDataSource = CourseNetworkDataSource(createApi(retrofit) as CourseService)
    val repository = CourseRepositoryImpl(networkDataSource)

    repository.fetchCourses().collect {
        it.fold(
            isLoading = { println("isLoading") },
            isFailure = { println("isFailure") },
            isSuccess = { courses ->
                println("isSuccess")
                courses.forEach { course ->
                    println("$course")
                }
            }
        )
    }
}
