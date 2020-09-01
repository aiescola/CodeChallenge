package com.aitor.domestikacodechallenge.domain.repository

import com.aitor.domestikacodechallenge.domain.common.Resource
import com.aitor.domestikacodechallenge.domain.model.Course
import com.aitor.domestikacodechallenge.domain.model.RepositoryError
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun fetchCourses(): Flow<Resource<RepositoryError, List<Course>>>
}