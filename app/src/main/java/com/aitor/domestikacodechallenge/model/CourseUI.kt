package com.aitor.domestikacodechallenge.model

import android.os.Parcelable
import com.aitor.domestikacodechallenge.domain.model.Course
import com.aitor.domestikacodechallenge.domain.model.Reviews
import com.aitor.domestikacodechallenge.domain.model.Teacher
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CourseUI(
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val trailerUrl: String,
    val description: String,
    val location: String,
    val teacher: TeacherUI,
    val reviews: ReviewsUI,
    val lessonsCount: Int,
    val students: Int,
    val audio: String,
    val subtitles: List<String>,
    val level: SkillLevelUI
) : Parcelable

@Parcelize
data class ReviewsUI(
    val positive: Int,
    val total: Int
) : Parcelable {
    val positivePct
        get() = "${positive * 100 / total}%"
}

@Parcelize
data class TeacherUI(
    val name: String,
    val avatarUrl: String
) : Parcelable

fun Reviews.toUI(): ReviewsUI = ReviewsUI(positive, total)

fun Teacher.toUI(): TeacherUI = TeacherUI(name, avatarUrl)

fun List<Course>.toUI(): List<CourseUI> = this.map {
    CourseUI(
        it.id,
        it.thumbnailUrl,
        it.title,
        it.trailerUrl,
        it.description,
        it.location,
        it.teacher.toUI(),
        it.reviews.toUI(),
        it.lessonsCount,
        it.students,
        it.audio,
        it.subtitles,
        SkillLevelUI.fromString(it.level)
    )
}
