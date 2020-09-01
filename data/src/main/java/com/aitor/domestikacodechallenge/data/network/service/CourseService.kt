package com.aitor.domestikacodechallenge.data.network.service

import com.aitor.domestikacodechallenge.data.network.model.CourseDTO
import retrofit2.Response
import retrofit2.http.GET

interface CourseService {
    @GET(value = "/challenge/home.json")
    suspend fun all(): Response<List<CourseDTO>>
}