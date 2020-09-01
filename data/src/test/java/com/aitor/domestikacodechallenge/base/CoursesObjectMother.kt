package com.aitor.domestikacodechallenge.base

import com.aitor.domestikacodechallenge.data.network.model.CourseDTO
import com.aitor.domestikacodechallenge.data.network.model.ReviewsDTO
import com.aitor.domestikacodechallenge.data.network.model.TeacherDTO

val teacherDTO = TeacherDTO(
    name = "name",
    avatarUrl = "avatarurl"
)

val reviewDTO = ReviewsDTO(
    positive = 1,
    total = 1
)

val courseDTO = CourseDTO(
    id = 1,
    thumbnailUrl = "thumbnailUrl",
    title = "test title",
    trailerUrl = "trailerUrl",
    description = "description",
    location = "location",
    teacher =  teacherDTO,
    reviews = reviewDTO,
    subtitles = listOf("a", "b", "c"),
    level = "level",
    lessonsCount = 1,
    students = 1,
    audio = "audio"
)
