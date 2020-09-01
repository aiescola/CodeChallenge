package com.aitor.domestikacodechallenge.data.network.datasource

import com.aitor.domestikacodechallenge.data.network.model.CourseDTO
import com.aitor.domestikacodechallenge.data.network.asResource
import com.aitor.domestikacodechallenge.data.network.service.CourseService
import com.aitor.domestikacodechallenge.domain.common.Resource
import com.aitor.domestikacodechallenge.domain.model.ApiError
import com.squareup.moshi.JsonEncodingException

class CourseNetworkDataSource(private val courseService: CourseService) {
    suspend fun fetchCourses(): Resource<ApiError, List<CourseDTO>> {
        return try { courseService.all().asResource() }
        catch (e: JsonEncodingException) { Resource.Failure(ApiError.NetworkError) }
    }

}
