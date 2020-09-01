package com.aitor.domestikacodechallenge.domain.model

data class Course (
    val id : Int,
    val thumbnailUrl : String,
    val title : String,
    val trailerUrl : String,
    val description : String,
    val location : String,
    val teacher : Teacher,
    val reviews : Reviews,
    val lessonsCount : Int,
    val students : Int,
    val audio : String,
    val subtitles : List<String>,
    val level : String
)

data class Reviews (
    val positive : Int,
    val total : Int
)

data class Teacher (
    val name : String,
    val avatarUrl : String
)