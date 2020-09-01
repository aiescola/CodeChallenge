package com.aitor.domestikacodechallenge.data.network.model

import com.aitor.domestikacodechallenge.domain.model.Course
import com.aitor.domestikacodechallenge.domain.model.Reviews
import com.aitor.domestikacodechallenge.domain.model.Teacher

data class CourseDTO (
    val id : Int,
    val thumbnailUrl : String,
    val title : String,
    val trailerUrl : String,
    val description : String,
    val location : String,
    val teacher : TeacherDTO,
    val reviews : ReviewsDTO,
    val lessonsCount : Int,
    val students : Int,
    val audio : String,
    val subtitles : List<String>,
    val level : String
)

data class ReviewsDTO (
    val positive : Int,
    val total : Int
)

data class TeacherDTO (
    val name : String,
    val avatarUrl : String
)

fun ReviewsDTO.toDomain(): Reviews = Reviews(positive, total)

fun TeacherDTO.toDomain(): Teacher = Teacher(name, avatarUrl)

fun List<CourseDTO>.toDomain(): List<Course> {
    return map{
        Course(
            it.id,
            it.thumbnailUrl,
            it.title,
            it.trailerUrl,
            it.description,
            it.location,
            it.teacher.toDomain(),
            it.reviews.toDomain(),
            it.lessonsCount,
            it.students,
            it.audio,
            it.subtitles,
            it.level
        )
    }
}
