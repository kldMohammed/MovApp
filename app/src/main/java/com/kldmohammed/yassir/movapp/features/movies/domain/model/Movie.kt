package com.kldmohammed.yassir.movapp.features.movies.domain.model

import java.util.*


data class Movie(
    
    val id: Long,
    
    val posterPath: String,
    
    val releaseDate: Date,
    
    val title: String,
    val overview: String?,
    val voteAverage: Double?,
)
