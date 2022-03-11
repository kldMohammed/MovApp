package com.kldmohammed.yassir.movapp.features.movies.domain.model

data class MovieDetails(
    val id: Long,
    
    val posterPath: String,
    
    val releaseDate: String,
    
    val title: String,
    val description: String,
)