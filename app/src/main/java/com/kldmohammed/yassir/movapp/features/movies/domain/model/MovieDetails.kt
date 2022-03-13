package com.kldmohammed.yassir.movapp.features.movies.domain.model

import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.GenresItem
import java.util.*

data class MovieDetails(
    val id: Long,
    
    val posterPath: String,
    
    val releaseDate: Date,
    
    val title: String,
    val description: String,
    val voteAverage: Double?,
    val backdropPath: String?,
    val genres: List<GenresItem?>?,
)