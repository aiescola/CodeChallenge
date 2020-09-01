package com.aitor.domestikacodechallenge.domain.usecases

import com.aitor.domestikacodechallenge.domain.common.Resource
import com.aitor.domestikacodechallenge.domain.model.Course
import com.aitor.domestikacodechallenge.domain.model.RepositoryError
import com.aitor.domestikacodechallenge.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class FetchCoursesUseCase(private val courseRepository: CourseRepository) {

    operator fun invoke(): Flow<Resource<RepositoryError, List<Course>>> =
        courseRepository.fetchCourses()
}
