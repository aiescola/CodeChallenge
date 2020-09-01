package com.aitor.domestikacodechallenge

import com.aitor.domestikacodechallenge.data.network.datasource.CourseNetworkDataSource
import com.aitor.domestikacodechallenge.data.network.model.toDomain
import com.aitor.domestikacodechallenge.data.repository.CourseRepositoryImpl
import com.aitor.domestikacodechallenge.domain.common.Resource
import com.aitor.domestikacodechallenge.domain.common.asFailure
import com.aitor.domestikacodechallenge.domain.common.asSuccess
import com.aitor.domestikacodechallenge.domain.model.ApiError
import com.aitor.domestikacodechallenge.domain.model.RepositoryError
import com.aitor.domestikacodechallenge.base.courseDTO
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class CourseRepositoryTest {

    @MockK
    lateinit var dataSource: CourseNetworkDataSource

    private val sut by lazy {
        CourseRepositoryImpl(dataSource)
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `returns success domain model when correct data`() = runBlocking {
        val testData = listOf(courseDTO).asSuccess()
        coEvery { dataSource.fetchCourses() } returns testData

        val result = sut.fetchCourses().toList()

        val expected = listOf(
            Resource.Loading,
            Resource.Success(testData.data.toDomain()))

        assertEquals(expected, result)
    }

    @Test
    fun `returns RepositoryError when ApiError`() = runBlocking {
        val testData = ApiError.NetworkError.asFailure()
        coEvery { dataSource.fetchCourses() } returns testData

        val result = sut.fetchCourses().toList()

        val expected = listOf(
            Resource.Loading,
            RepositoryError.NetworkError(testData.error).asFailure())

        assertEquals(expected, result)
    }

}
