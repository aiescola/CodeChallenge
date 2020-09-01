package com.aitor.domestikacodechallenge.data.repository

import com.aitor.domestikacodechallenge.data.network.datasource.CourseNetworkDataSource
import com.aitor.domestikacodechallenge.data.network.model.toDomain
import com.aitor.domestikacodechallenge.domain.common.Resource
import com.aitor.domestikacodechallenge.domain.common.map
import com.aitor.domestikacodechallenge.domain.model.Course
import com.aitor.domestikacodechallenge.domain.model.RepositoryError
import com.aitor.domestikacodechallenge.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CourseRepositoryImpl(private val courseNetworkDataSource: CourseNetworkDataSource): CourseRepository {
    override fun fetchCourses(): Flow<Resource<RepositoryError, List<Course>>> {
       return flow {
           emit(Resource.Loading)

           val resource = courseNetworkDataSource.fetchCourses().map(
               mapError = {
                   RepositoryError.NetworkError(it)
               },
               mapSuccess = {
                   it.toDomain()
               }
           )

           emit(resource)
       }
    }

}
