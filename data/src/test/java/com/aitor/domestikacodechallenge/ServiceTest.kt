package com.aitor.domestikacodechallenge


import com.aitor.domestikacodechallenge.base.MockWebServerTest
import com.aitor.domestikacodechallenge.data.network.asResource
import com.aitor.domestikacodechallenge.data.network.createApi
import com.aitor.domestikacodechallenge.data.network.createRetrofit
import com.aitor.domestikacodechallenge.data.network.service.CourseService
import com.aitor.domestikacodechallenge.domain.common.asFailure
import com.aitor.domestikacodechallenge.domain.common.isSuccess
import com.aitor.domestikacodechallenge.domain.model.ApiError
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ServiceTest : MockWebServerTest() {

    private lateinit var courseService: CourseService

    @Before
    override fun setUp() {
        super.setUp()
        val retrofit = createRetrofit(baseEndpoint, false)

        courseService = createApi(retrofit) as CourseService
    }

    @Test
    fun `request first page is sent and method is get`() = runBlocking {
        enqueueMockResponse(200, "all_courses_200.json")
        courseService.all()

        assertGetRequestSentTo("/challenge/home.json")
    }

    @Test
    fun `returns all characters when status 200 and json okay`() = runBlocking {
        enqueueMockResponse(200, "all_courses_200.json")

        val response = courseService.all().asResource()

        assert(response.isSuccess)
    }

    @Test
    fun `returns error when status 400`() = runBlocking {
        enqueueMockResponse(400)

        val response = courseService.all().asResource()

        assertEquals(
            ApiError.UnknownError(400).asFailure(),
            response)
    }

    @Test
    fun `returns error when status 404`() = runBlocking {
        enqueueMockResponse(404)

        val response = courseService.all().asResource()

        assertEquals(
            ApiError.NotFoundError.asFailure(),
            response)
    }
}
